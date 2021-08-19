package cn.inkroom.tool.noveldownload.www.xmbxs;

import cn.inkroom.tool.noveldownload.NovelDownload;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author inkbox
 * @date 2021/8/17
 */
public class Download extends NovelDownload {

    /**
     * 当前第几章
     */
    private int index = 1;
    private Element top;

    @Override
    public void setTop(String url) {
        if (url != null) {
            try {
                System.out.println("获取目录页");
                top = getDocument(url);
                System.out.println("获取目录页成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getTitle(Element element) {


        if (top != null) {


            System.out.println("index = " + index + "  " + Math.ceil( index / 8f) + "  " + ((index % 8 + 3) % 4));
            Element t = top.select(".mread tr").get(((int) Math.ceil(index / 8f))).select("td").get(((index % 8 + 3) % 4)).select("a").get(index % 8 > 4 ? 1 : 0);
            index++;
            return t.html().replace("章", "章 ");
        }


        return null;

//        Elements mview = element.getElementsByClass("mview");
//        Elements select = mview.select("tr:eq(1) strong");
//
//        return select.get(0).html();
    }

    @Override
    public String getContent(Element element) {
        Elements content = null;
        do {
            content = element.getElementsByClass("content");
            if (content.size() == 0) {
                try {
                    content = getDocument(this.url).getElementsByClass("content");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } while (content != null && content.size() == 0);

        String html = content.get(0).html();


        return html.replaceAll("<br><br>", "\n")
                .replaceAll("<img src=\"image/cao\\.jpg\">", "操")
                .replaceAll("<img src=\"image/ri\\.jpg\">", "日")
                .replaceAll("<img src=\"image/xing\\.jpg\">", "性")
                .replaceAll("<img src=\"image/man\\.jpg\">", "满")
                .replaceAll("<img src=\"image/xi\\.jpg\">", "吸")
                .replaceAll("<img src=\"image/shuang\\.jpg\">", "爽")
                .replaceAll("<img src=\"image/qiang\\.jpg\">", "枪")
                .replaceAll("<img src=\"image/jin\\.jpg\">", "禁")
                .replaceAll("<img src=\"image/liu\\.jpg\">", "流")
                .replaceAll("<img src=\"image/you\\.jpg\">", "诱")
                .replaceAll("<img src=\"image/huan\\.jpg\">", "欢")
                .replaceAll("<img src=\"image/ji\\.jpg\">", "激")
                .replaceAll("<img src=\"image/ting\\.jpg\">", "挺")
                .replaceAll("<img src=\"image/rou\\.jpg\">", "肉")
                .replaceAll("<img src=\"image/xie2\\.jpg\">", "邪")
                .replaceAll("<img src=\"image/chun2\\.jpg\">", "唇")
                .replaceAll("<img src=\"image/yao\\.jpg\">", "腰")
                .replaceAll("<img src=\"image/tun\\.jpg\">", "吞")
                .replaceAll("<img src=\"image/yu\\.jpg\">", "欲")
                .replaceAll("<img src=\"image/she\\.jpg\">", "射")
                .replaceAll("<img src=\"image/xiong\\.jpg\">", "胸")
                .replaceAll("<img src=\"image/shu\\.jpg\">", "熟")
                .replaceAll("<img src=\"image/yan\\.jpg\">", "艳")
                .replaceAll("<img src=\"image/lou\\.jpg\">", "漏")
                .replaceAll("<img src=\"image/chou\\.jpg\">", "抽")
                .replaceAll("<img src=\"image/nen\\.jpg\">", "嫩")
                .replaceAll("<img src=\"image/tun2\\.jpg\">", "臀")
                .replaceAll("<img src=\"image/tun2\\.jpg\">", "臀")
                .replaceAll("<img src=\"image/tun2\\.jpg\">", "臀")
                .replaceAll("<img src=\"image/tun2\\.jpg\">", "臀")
                .replaceAll("<img src=\"image/tun2\\.jpg\">", "臀")
                .replaceAll("<img src=\"image/tun2\\.jpg\">", "臀")
                .replaceAll("<img src=\"image/tun2\\.jpg\">", "臀")
                .replaceAll("<img src=\"image/tun2\\.jpg\">", "臀")
                .replaceAll("<img src=\"image/tun2\\.jpg\">", "臀")
                .replaceAll("<img src=\"image/tun2\\.jpg\">", "臀")


                .replaceAll("<img src=\"image/ai\\.jpg\">", "爱")


                .replaceAll("<img src=\"image/bang\\.jpg\">", "棒 ")


                .replaceAll("<img src=\"image/bao\\.jpg\">", "抱")


                .replaceAll("<img src=\"image/bi2\\.jpg\">", "逼")


                .replaceAll("<img src=\"image/bi\\.jpg\">", "逼")


                .replaceAll("<img src=\"image/bo\\.jpg\">", "勃")


                .replaceAll("<img src=\"image/cao2\\.jpg\">", "操")


                .replaceAll("<img src=\"image/cao\\.jpg\">", "操")


                .replaceAll("<img src=\"image/cha\\.jpg\">", "插")


                .replaceAll("<img src=\"image/chan\\.jpg\">", "缠")


                .replaceAll("<img src=\"image/chao\\.jpg\">", "潮")


                .replaceAll("<img src=\"image/chi\\.jpg\">", "耻")


                .replaceAll("<img src=\"image/chou\\.jpg\">", "抽")


                .replaceAll("<img src=\"image/chuang\\.jpg\">", "床")


                .replaceAll("<img src=\"image/chuan\\.jpg\">", "喘")


                .replaceAll("<img src=\"image/chun2\\.jpg\">", "唇")


                .replaceAll("<img src=\"image/chun\\.jpg\">", "春")


                .replaceAll("<img src=\"image/cu\\.jpg\">", "粗")


                .replaceAll("<img src=\"image/cuo\\.jpg\">", "搓")


                .replaceAll("<img src=\"image/dang2\\.jpg\">", "党")


                .replaceAll("<img src=\"image/dang\\.jpg\">", "荡")


                .replaceAll("<img src=\"image/dong2\\.jpg\">", "胴")


                .replaceAll("<img src=\"image/dong\\.jpg\">", "洞")


                .replaceAll("<img src=\"image/feng\\.jpg\">", "缝")


                .replaceAll("<img src=\"image/fu\\.jpg\">", "阜")


                .replaceAll("<img src=\"image/gang\\.jpg\">", "肛")


                .replaceAll("<img src=\"image/gan\\.jpg\">", "感")


                .replaceAll("<img src=\"image/gao2\\.jpg\">", "睾")


                .replaceAll("<img src=\"image/gen\\.jpg\">", "根")


                .replaceAll("<img src=\"image/gong\\.jpg\">", "宫")


                .replaceAll("<img src=\"image/gui\\.jpg\">", "龟")


                .replaceAll("<img src=\"image/gun\\.jpg\">", "棍")


                .replaceAll("<img src=\"image/ji2\\.jpg\">", "鸡")


                .replaceAll("<img src=\"image/ji3\\.jpg\">", "妓")


                .replaceAll("<img src=\"image/jian2\\.jpg\">", "奸")


                .replaceAll("<img src=\"image/jian\\.jpg\">", "贱")


                .replaceAll("<img src=\"image/jing\\.jpg\">", "精")


                .replaceAll("<img src=\"image/jin\\.jpg\">", "禁")


                .replaceAll("<img src=\"image/kua\\.jpg\">", "胯")


                .replaceAll("<img src=\"image/ku\\.jpg\">", "库")


                .replaceAll("<img src=\"image/lang\\.jpg\">", "浪")


                .replaceAll("<img src=\"image/liao\\.jpg\">", "撩")


                .replaceAll("<img src=\"image/luan\\.jpg\">", "乱")


                .replaceAll("<img src=\"image/lu\\.jpg\">", "撸")


                .replaceAll("<img src=\"image/luo\\.jpg\">", "裸")


                .replaceAll("<img src=\"image/man\\.jpg\">", "满")


                .replaceAll("<img src=\"image/mao\\.jpg\">", "毛")


                .replaceAll("<img src=\"image/mi2\\.jpg\">", "迷")


                .replaceAll("<img src=\"image/mi\\.jpg\">", "蜜")


                .replaceAll("<img src=\"image/min\\.jpg\">", "敏")


                .replaceAll("<img src=\"image/nai\\.jpg\">", "奶")


                .replaceAll("<img src=\"image/nen\\.jpg\">", "嫩")


                .replaceAll("<img src=\"image/niao\\.jpg\">", "尿")


                .replaceAll("<img src=\"image/nong\\.jpg\">", "弄")


                .replaceAll("<img src=\"image/nue\\.jpg\">", "虐")


                .replaceAll("<img src=\"image/pen\\.jpg\">", "喷")


                .replaceAll("<img src=\"image/pi\\.jpg\">", "屁")


                .replaceAll("<img src=\"image/qi2\\.jpg\">", "妻")


                .replaceAll("<img src=\"image/rou2\\.jpg\">", "揉")


                .replaceAll("<img src=\"image/rou\\.jpg\">", "肉")


                .replaceAll("<img src=\"image/ru2\\.jpg\">", "蠕")


                .replaceAll("<img src=\"image/rui\\.jpg\">", "蕊")


                .replaceAll("<img src=\"image/ru\\.jpg\">", "乳")


                .replaceAll("<img src=\"image/sai\\.jpg\">", "塞")


                .replaceAll("<img src=\"image/sao\\.jpg\">", "骚")


                .replaceAll("<img src=\"image/se\\.jpg\">", "色")


                .replaceAll("<img src=\"image/shi\\.jpg\">", "湿")


                .replaceAll("<img src=\"image/shuang\\.jpg\">", "爽")


                .replaceAll("<img src=\"image/shun\\.jpg\">", "吮")


                .replaceAll("<img src=\"image/tian\\.jpg\">", "舔")


                .replaceAll("<img src=\"image/tuo\\.jpg\">", "脱")


                .replaceAll("<img src=\"image/wei\\.jpg\">", "慰")


                .replaceAll("<img src=\"image/xie\\.jpg\">", "泻")


                .replaceAll("<img src=\"image/xi\\.jpg\">", "吸")


                .replaceAll("<img src=\"image/xing\\.jpg\">", "性")


                .replaceAll("<img src=\"image/xiong\\.jpg\">", "胸")


                .replaceAll("<img src=\"image/xue\\.jpg\">", "穴")


                .replaceAll("<img src=\"image/ya\\.jpg\">", "压")


                .replaceAll("<img src=\"image/yang2\\.jpg\">", "痒")


                .replaceAll("<img src=\"image/yang\\.jpg\">", "阳")


                .replaceAll("<img src=\"image/yan\\.jpg\">", "艳")
                .replaceAll("<img src=\"image/ye\\.jpg\">", "液")
                .replaceAll("<img src=\"image/yi\\.jpg\">", "旖")
                .replaceAll("<img src=\"image/yin2\\.jpg\">", "淫")
                .replaceAll("<img src=\"image/yin3\\.jpg\">", "吟")
                .replaceAll("<img src=\"image/ying\\.jpg\">", "迎")
                .replaceAll("<img src=\"image/yin\\.jpg\">", "阴")
                .replaceAll("<img src=\"image/zhang\\.jpg\">", "胀")
                .replaceAll("<img src=\"image/jiao\\.jpg\">", "交");
    }

    @Override
    public String getNextUrl(Element element) {
        Elements viewxia1 = element.getElementsByClass("viewxia1");
        Elements a = viewxia1.select("a");
        if (a.size() <= 1) return null;
        String s = a.get(1).absUrl("href");

        return s.replace("baxianxs", "xmbxs").replace("page.asp", "mbuxs.asp");
    }
}
