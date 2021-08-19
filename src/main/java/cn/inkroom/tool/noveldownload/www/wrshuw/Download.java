package cn.inkroom.tool.noveldownload.www.wrshuw;

import cn.inkroom.tool.noveldownload.NovelDownload;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author inkbox
 * @date 2021/7/12
 */
public class Download extends NovelDownload {
    @Override
    public String getTitle(Element element) {
        return "";
    }

    @Override
    public String getContent(Element element) {
        Element content = element.getElementById("content");
        content.childNode(content.childNodeSize() - 1).remove();
        return content.html().replaceAll(" &nbsp;&nbsp;&nbsp;&nbsp;", "").replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "").replaceAll("<br>", "");
    }

    @Override
    public String getNextUrl(Element element) {
        Element element1 = element.select(".page_next_preview>p>span>a").get(2);
        if (element1.attr("href").equals("0.html")) return null;
        return element1.absUrl("href");
    }
}
