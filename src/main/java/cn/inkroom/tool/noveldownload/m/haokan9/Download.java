package cn.inkroom.tool.noveldownload.m.haokan9;

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
        return element.getElementsByClass("titlename").get(0).html().replaceAll(" \\(\\d?/\\d?\\)", "").replace("正文 ", "");
    }

    @Override
    public String getContent(Element element) {

        Element nr1 = element.getElementsByClass("articlecon").get(0);

        StringBuilder builder = new StringBuilder();
        Elements p = nr1.getElementsByTag("p");
        for (int i = 0; i < p.size() - 1; i++) {//去除最后一个元素
            if (!p.get(i).html().contains("本章未完") || !p.get(i).html().contains("本章完"))
                builder.append(p.get(i).html()
                        .replace("《 乡 村 小 说 网 手*机*阅#读 ｍ.ｘiangｃunＸｉａｏshuo．ｏｒｇ》", "")
                        .replace("( 神鹰帝国 9haokan/4/4342/ 移动版阅读m.9haokan )", "")
                ).append("\n");
        }

        return builder.toString();
    }

    @Override
    public String getNextUrl(Element element) {
        Elements bottem2 = element.getElementsByClass("articlebtn");
        String s = bottem2.get(0).getElementsByTag("a").get(3).absUrl("href");
        return s.contains("read") ? s : null;
    }

    @Override
    public boolean hasMorPage(String nextUrl) {
        return !nextUrl.matches(".*read_\\d+\\.html");
    }

    public static void main(String[] args) {
        //https://m.9haokan.com/novel/shenyingdiguo/read_2.html
        System.out.println("https://m.9haokan.com/novel/shenyingdiguo/read_1_75_2.html".matches(".*read_\\d+\\.html"));
    }
}


