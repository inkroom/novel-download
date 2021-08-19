package cn.inkroom.tool.noveldownload.www.zw;

import cn.inkroom.tool.noveldownload.NovelDownload;
import org.jsoup.nodes.Element;

/**
 * www.899zw.com
 *
 * @author inkbox
 * @date 2021/8/18
 */
public class Download extends NovelDownload {
    @Override
    public String getTitle(Element element) {
        return element.getElementsByTag("h1").get(0).html().trim();
    }

    @Override
    public String getContent(Element element) {
        Element content = element.getElementById("content");

        return content.html().replaceAll("<br><br>", "\n")
                .replace(getTitle(element).replace(" ", ""), "")
                .replaceAll("<br>\n?","");
    }

    @Override
    public String getNextUrl(Element element) {
        return element.getElementById("nextPage").absUrl("href");
    }
}
