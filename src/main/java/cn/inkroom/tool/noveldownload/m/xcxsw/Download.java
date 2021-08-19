package cn.inkroom.tool.noveldownload.m.xcxsw;

import cn.inkroom.tool.noveldownload.NovelDownload;
import org.jsoup.nodes.Element;

/**
 * @author inkbox
 * @date 2021/7/12
 */
public class Download extends NovelDownload {
    @Override
    public String getTitle(Element element) {
        return element.getElementsByClass("title").get(0).html()
                .replace("正文 ", "")
                .replace("章 ", "章")
                .replace("章", "章 ")
                .replaceAll("^(\\d+)、","第$1章 ")
                ;
    }

    @Override
    public String getContent(Element element) {
        Element chaptercontent = element.getElementById("chaptercontent");
        chaptercontent.getElementsByTag("p").remove();
        String html = chaptercontent.html();
//        System.out.println(html);
        return html.replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "        ")
                .replaceAll("<br>", "")
                .replaceAll("乡村小说网\\(m.xcxsw.cc\\)免费好看的小说阅读网站\\.《\\+乡\\+村\\+小\\+说\\+网&nbsp;手*机*阅#读&nbsp;m.xiangcunxiaoshuo．org》","")
                .replaceAll("乡村小说网\\(m.xcxsw.cc\\)免费好看的小说阅读网站\\.","")
                .replaceAll("《\\+乡\\+村\\+小\\+说\\+网&nbsp;手*机*阅#读&nbsp;m.xiangcunxiaoshuo．org》","")
                .replaceAll("&nbsp;","");
    }

    @Override
    public String getNextUrl(Element element) {
        String s = element.getElementById("pt_next").absUrl("href");
        if (s.endsWith(".html")) return s;
        return null;
    }
}
