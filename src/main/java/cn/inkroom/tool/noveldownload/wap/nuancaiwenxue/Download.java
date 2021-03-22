package cn.inkroom.tool.noveldownload.wap.nuancaiwenxue;

import cn.inkroom.tool.noveldownload.NovelDownload;
import org.jsoup.nodes.Element;

public class Download extends NovelDownload {
    @Override
    public String getTitle(Element element) {
        return element.getElementById("nr_title").html().replace("：", " ");
    }

    @Override
    public String getContent(Element element) {
        Element nr1 = element.getElementById("nr1");

//        String end = nr1.childNode(nr1.childNodeSize() - 1).toString();

        return nr1.html().replace(nr1.childNode(0).toString().substring(1), "")
             //   .replaceAll(end,"")
                .replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "        ")
                .replaceAll("<br>", "")
                .replaceAll("&nbsp;", "");
    }

    @Override
    public String getNextUrl(Element element) {
        String s = element.getElementById("pt_next").absUrl("href");
        if (s.endsWith(".html"))
            return s;
        return null;
    }
}
