package cn.inkroom.tool.noveldownload.m.niluxs;

import cn.inkroom.tool.noveldownload.NovelDownload;
import org.jsoup.nodes.Element;

/**
 * @author inkbox
 * @date 2021/8/18
 */
public class Download extends NovelDownload {
    @Override
    public boolean hasMorPage(String nextUrl) {
        return nextUrl.matches(".*_\\d+\\.html$");
    }

    @Override
    public String getTitle(Element element) {
        return element.getElementById("nr_title").html().replaceFirst("第\\(\\d+/\\d+\\)页", "");
    }

    @Override
    public String getContent(Element element) {
        String nr1 = element.getElementById("nr1").html();
        return nr1
                .replaceAll("\\n<br>\\n<br>&nbsp;&nbsp;&nbsp;&nbsp;--&gt;&gt;\\(第\\d+/\\d+页\\)（本章未完，请点击下一页继续阅读）", "")
                .replaceAll("(<br>)?&nbsp;&nbsp;&nbsp;&nbsp;", "\t\t")
                .replaceAll("<br>\\n?","")
                .replaceAll("&nbsp;&nbsp;","");
    }

    @Override
    public String getNextUrl(Element element) {
        String s = element.getElementById("pb_next").absUrl("href");
        if (s.endsWith("html")) {
            return s;
        }
        return null;
    }
}
