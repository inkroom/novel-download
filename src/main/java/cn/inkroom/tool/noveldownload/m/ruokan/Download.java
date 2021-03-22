package cn.inkroom.tool.noveldownload.m.ruokan;

import cn.inkroom.tool.noveldownload.NovelDownload;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 基于https://www.ruokan.net
 *
 * @Date 2021-03-14
 */
public class Download extends NovelDownload {
    @Override
    public String getTitle(Element element) {
        return element.getElementsByTag("h1").get(0).html().replaceAll("\\(第\\d节\\)", "").replace("章", "章 ");
    }

    @Override
    public String getContent(Element element) {

        Element nr1 = element.getElementById("content");

        StringBuilder builder = new StringBuilder();
        Elements p = nr1.getElementsByTag("p");
        for (int i = 0; i < p.size() - 1; i++) {//去除最后一个元素
            builder.append(p.get(i).html()).append("\n");
        }

        return builder.toString();
    }

    @Override
    public String getNextUrl(Element element) {
        Elements bottem2 = element.getElementsByClass("bottem2");
        return element.getElementsByClass("bottem2").get(0).getElementsByTag("a").get(3).absUrl("href");
    }
}
