package cn.inkroom.tool.noveldownload;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.SSLProtocolException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;


/**
 * @author 墨盒
 * @date 2020/12/24
 */
public abstract class NovelDownload {

    private File file;

    private String firstUrl;

    protected String url;

    private String name;

    /**
     * 设置目录
     * @param url
     */
    public void setTop(String url){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Document getDocument(String url) throws IOException {
        int timeout = 3000;
        this.url = url;
        while (true) {
            try {
                //睡眠一下
//                Thread.sleep(timeout);

                System.out.println("正在加载  " + url);
                return Jsoup.parse(new URL(url), timeout);
            } catch ( IOException e) {
                //增加超时时间继续
                timeout += 3000;
                System.out.println("加载 " + url + " 超时 ，时间设定为=" + timeout);

            }
        }


    }


    public abstract String getTitle(Element element);

    /**
     * 获取文章内容
     *
     * @param element
     * @return
     */
    public abstract String getContent(Element element);

    /**
     * 获取下一页的url，返回null代表下载结束
     *
     * @param element
     * @return
     */
    public abstract String getNextUrl(Element element);

    public void setFile(File file) {
        this.file = file;
    }

    public void setFirstUrl(String firstUrl) {
        this.firstUrl = firstUrl;
    }


    public boolean download() {

        String url = this.firstUrl;


        StringBuilder builder = new StringBuilder();
        // 每一章写入一次
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {

            do {


                Document document = getDocument(url);
                //获取标题
                String title = getTitle(document);

                System.out.println("获取 " + title);

                writer.write(title);
                writer.newLine();

                //获取内容
                builder.append(getContent(document));

                while (true) {
                    String nextUrl = getNextUrl(document);

                    if (nextUrl == null) {
                        url = null;
                        break;
                    }
                    //判断是下一页，还是下一章
                    if (hasMorPage(nextUrl)) {//还有下一页
                        document = getDocument(nextUrl);
                        builder.append(getContent(document));
                    } else {//下一章了
                        url = nextUrl;
                        break;
                    }
                }
                writer.write(builder.toString());
                writer.newLine();
                writer.flush();//刷新到文件
                builder.delete(0, builder.length());


            } while (url != null);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;


    }

    /**
     * 对于部分网站会对一章的内容进行分页，这里用来判断当前章是否已经下载完成，同时标题应该去掉可能存在的页码字样
     *
     * @param nextUrl 下一章或者下一页的url
     * @return
     */
    public boolean hasMorPage(String nextUrl) {
        return nextUrl.matches(".*_[1-9]\\.html$");
    }

}
