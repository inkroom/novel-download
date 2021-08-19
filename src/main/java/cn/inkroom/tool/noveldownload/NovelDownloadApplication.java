package cn.inkroom.tool.noveldownload;


import cn.inkroom.tool.noveldownload.bean.DownloadBean;
import cn.inkroom.tool.noveldownload.wap.nuancaiwenxue.Download;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.SSLProtocolException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;

public class NovelDownloadApplication {

    public static void mai1n(String[] args) {

        File file = new File("D:\\娱乐\\noval\\护花野蛮人.txt");
        String url = "http://m.binifu.com/read/21171/8072190.html";//第一页
        String finishUrl = "https://m.hlxs9.com/mulu/583.html";//目录页

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {


//            String url = "https://www.lingdiankanshu.co/24095/1366063.html";
            StringBuilder builder = new StringBuilder();

            do {

                Document document = getDocument(url);
                //获取标题
                document.select(".headline>small").remove();
                String title = document.select(".headline").eq(0).html();

                System.out.println("获取 " + title);

                writer.write(title);
                writer.newLine();

                //获取内容
                Element content = document.getElementById("content");

                builder.append(getContent(content));

                //判断是下一页，还是下一章

                while (true) {
                    String nextUrl = document.select("a[rel=next]").get(0).absUrl("href");
//                    String nextUrl = document.select(".section-opt,.m-bottom-opt").select("a").get(2).absUrl("href");

                    if (nextUrl.matches(".*_[1-9]\\.html$")) {//还有下一页
                        document = getDocument(nextUrl);
                        content = document.getElementById("content");

                        builder.append(getContent(content));

                    } else if (nextUrl.equals(finishUrl)) {//所有章节结束
                        url = null;
                        break;

                    } else {//下一章了
                        url = nextUrl;
//                        url = null;
                        break;
                    }

                    //-----
//                    else{
//                        url = null;
//                        break;
//                    }

                }

                writer.write(builder.toString());
                writer.newLine();
                builder.delete(0, builder.length() - 1);


                System.out.println(builder);

            } while (url != null);


            System.out.println("小说下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        //读取小说目录
        try {
            builder.append(new String(IOUtils.resourceToByteArray("/source.json")));

        } catch (FileNotFoundException e) {
            System.out.println("source.json文件不存在");
            return;
        } catch (IOException e) {
            System.out.println("source.json有问题");
            return;
        }


        List<DownloadBean> downloadBeans = JSONObject.parseArray(builder.toString(), DownloadBean.class);


        for (int i = downloadBeans.size() - 1; i < downloadBeans.size(); i++) {
            //加载对应的下载器
            try {
                System.out.println("cn.inkroom.tool.noveldownload." + downloadBeans.get(i).getDownloader() + ".Download");
                Class<?> aClass = NovelDownloadApplication.class.getClassLoader().loadClass("cn.inkroom.tool.noveldownload." + downloadBeans.get(i).getDownloader() + ".Download");
                NovelDownload d = ((NovelDownload) aClass.getDeclaredConstructor().newInstance());

                d.setFirstUrl(downloadBeans.get(i).getFirstUrl());
                d.setFile(new File(downloadBeans.get(i).getFile()));
                d.setTop(downloadBeans.get(i).getTop());

                try {
                    d.download();
                } catch (Exception e) {
                    System.out.println(downloadBeans.get(i).getFile() + " 下载失败");
                    e.printStackTrace();
                }

            } catch (ClassNotFoundException e) {
                System.out.println(downloadBeans.get(i).getDownloader() + " 加载失败");
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        System.out.println("小说下载完成");

//
//        NovelDownload novelDownload = new Download();
////        novalDownload.setFile(new File("/home/ink/all/娱乐/novel/无限穿越之后宫.txt"));
////        novalDownload.setFirstUrl("https://www.ruokan.net/book/36/36837/2.html");
//
//
//        novelDownload.setFile(new File("/home/ink/all/娱乐/novel/都市寻艳录.txt"));
//        novelDownload.setFirstUrl("http://wap.nuancaiwenxue.com/book/0/273/80088.html");
//
//
//        try {
//            novelDownload.download();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        novelDownload = new Download();
////        novalDownload.setFile(new File("/home/ink/all/娱乐/novel/无限穿越之后宫.txt"));
////        novalDownload.setFirstUrl("https://www.ruokan.net/book/36/36837/2.html");
//
//
//        novelDownload.setFile(new File("/home/ink/all/娱乐/novel/武林风流传.txt"));
//        novelDownload.setFirstUrl("http://wap.nuancaiwenxue.com/book/0/181/63478.html");
//
//
//        try {
//            novelDownload.download();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        novelDownload.setFile(new File("/h/ink/all/娱乐/noval/极限诱惑.txt"));
//        novelDownload.setFirstUrl("http://wap.nuancaiwenxue.com/book/0/311/89381.html");
//
//
//        try {
//            novelDownload.download();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        novelDownload.setFile(new File("/home/ink/all/娱乐/novel/我的校花姐姐.txt"));
//        novelDownload.setFirstUrl("http://wap.nuancaiwenxue.com/book/0/280/81195.html");
//
//
//        try {
//            novelDownload.download();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        novelDownload.setFile(new File("/home/ink/all/娱乐/novel/龙入红尘.txt"));
//        novelDownload.setFirstUrl("http://wap.nuancaiwenxue.com/book/0/198/67093.html");
//
//
//        try {
//            novelDownload.download();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        novelDownload.setFile(new File("/home/ink/all/娱乐/novel/黑科技研发中心.txt"));
//        novelDownload.setFirstUrl("http://wap.nuancaiwenxue.com/book/5/5769/5076107.html");
//
//
//        try {
//            novelDownload.download();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        novelDownload.setFile(new File("/home/ink/all/娱乐/novel/姐夫的荣耀续集之女王归来.txt"));
//        novelDownload.setFirstUrl("http://wap.nuancaiwenxue.com/book/0/242/75030.html");
//
//        try {
//            novelDownload.download();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static Document getDocument(String url) throws IOException {
        int timeout = 3000;

        while (true) {
            try {
                System.out.println("正在加载  " + url);
                return Jsoup.parse(new URL(url), timeout);
            } catch (SSLProtocolException | SocketTimeoutException e) {
                //增加超时时间继续
                timeout += 3000;
                System.out.println("加载 " + url + " 超时 ，时间设定为=" + timeout);

            }
        }


    }

    public static String getContent(Element content) {

//        System.out.println(content.html());
        //去除div script 标签
        content.select("div").remove();
        content.select("script").remove();
        //优化换行符
//        return content.html()
//                .replaceAll("<br>&nbsp;&nbsp;&nbsp;&nbsp;", " ")
//                .replaceAll("\n<br>", "").replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "  ");

//        return content.html().replaceAll("\n<br>　　", "       ")
//                .replaceAll("\n<br>", "\n");

        return content.html()
//                .replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;","       ")
//                .replaceAll("\n<br>&nbsp;&nbsp;&nbsp;&nbsp;","      ")
//                .replaceAll("<br>&nbsp;&nbsp;&nbsp;&nbsp;","        ")
//                .replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;","        ")
//                .replaceAll("\n<br>","\n")
//                .replaceAll("第\\([0-9]*/[0-9]*\\)页","")
                ;
    }

}
