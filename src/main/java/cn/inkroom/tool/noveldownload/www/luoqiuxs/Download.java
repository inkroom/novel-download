package cn.inkroom.tool.noveldownload.www.luoqiuxs;

import cn.inkroom.tool.noveldownload.NovelDownload;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author inkbox
 * @date 2021/8/18
 */
public class Download extends NovelDownload {
    @Override
    public String getTitle(Element element) {
        String h1 = element.getElementsByTag("h1").get(0).html().trim();
        return h1.replaceAll("(第|-)? ?(\\d+) ", "第$2章 ")
                .replaceAll("^\\d+$", "第$0章");
    }

    @Override
    public String getContent(Element element) {
        return element.getElementById("content").html().replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "\t\t")
                .replaceAll("^<br> \\n", "")
                .replaceAll("( 风流情圣 http://www.luoqiuxs.com/16_16519/ 移动版阅读m.luoqiuxs.com )", "")
                .replaceAll("( 风流情圣  http://www\\.luoqiuxs\\.com/16_16519/ 移动版阅读m\\.luoqiuxs\\.com )", "")
                .replaceAll("<br> \\n", "")
                .replaceAll("<br>","")
                .replaceAll("《\\+乡\\+村\\+小\\+说\\+网 手\\*机\\*阅#读 ｍ\\.ｘiangｃunＸｉａｏshuo\\．ｏｒｇ》", "");
    }

    @Override
    public String getNextUrl(Element element) {
        Elements select = element.select(".bottem2>a");
        String href = select.get(3).absUrl("href");
        if (href.endsWith(".html")) {
            return href;
        }
        return null;
    }
}
