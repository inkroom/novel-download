package cn.inkroom.tool.noveldownload.m.binifu;

import cn.inkroom.tool.noveldownload.NovelDownload;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author 墨盒
 * @date 2020/12/24
 */
public class Download extends NovelDownload {

    @Override
    public String getTitle(Element element) {
        element.select(".headline>small").remove();
        return element.select(".headline").eq(0).html();
    }

    @Override
    public String getContent(Element element) {

        StringBuilder builder = new StringBuilder();
        Elements select = element.select("#content>p");

        for (Element value : select) {
            builder.append("        ").append(value.html()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public String getNextUrl(Element element) {
        String href = element.select(".pager>a").get(2).absUrl("href");
        return "http://m.binifu.com/novel/21171.html".equals(href)?null:href;
    }
}
