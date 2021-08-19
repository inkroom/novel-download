package cn.inkroom.tool.noveldownload.www.tbxsw;

import cn.inkroom.tool.noveldownload.NovelDownload;
import org.jsoup.nodes.Element;

/**
 * @author inkbox
 * @date 2021/8/18
 */
public class Download extends NovelDownload {
    @Override
    public String getTitle(Element element) {
        String h1 = element.getElementsByTag("h1").get(0).html();
        if (h1.startsWith("第"))
            return h1;
        return "第" + h1;
    }

    @Override
    public String getContent(Element element) {
        String content = element.getElementById("content").html();
        String s = content.replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;欢迎大家来到官术网：http://www\\.3dllc\\.com", "")
                .replaceAll("<br>&nbsp;&nbsp;&nbsp;&nbsp;", "\t\t")
                .replaceAll("<br>\\n", "")
                ;
        return s.substring(1);
    }

    @Override
    public String getNextUrl(Element element) {
        Element element1 = element.select(".bottem2>a").get(2);
        String href = element1.absUrl("href");
        if (href.endsWith("html"))
            return href;
        return null;
    }
}
