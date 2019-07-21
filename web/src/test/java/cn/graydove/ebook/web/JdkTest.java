package cn.graydove.ebook.web;

import cn.graydove.ebook.web.model.dto.BookDTO;
import cn.graydove.ebook.web.model.entity.Book;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JdkTest {

    @Test
    public void StreamTest(){
        List<Book> books = new ArrayList<>();

        Book book = new Book();
//        book.setAuthor("222");
        book.setName("111");
        book.setFirstChapter(1);
        books.add(book);

//        book.setAuthor("333");
        book.setName("222");
        book.setFirstChapter(2);
        books.add(book);

        BookDTO dto = new BookDTO();
        List<BookDTO> l2 = dto.writeToDTOList(books);
        System.out.println(l2);
        System.out.println(dto.writeToDomainList(l2));
    }

    @Test
    public void DTOtest(){
        String s = "\r\n\t\t\t\t    第一章星空中的青铜巨棺\n　　\n　　    生命是世间最伟大的奇迹。．\n　　\n　　    四方上下曰宇。宇虽有实，而无定处可求。往古来今曰宙。宙虽有增长，不知其始之所至。\n　　\n　　    浩瀚的宇宙，无垠的星空，许多科学家推测，地球可能是唯一的生命源地。\n　　\n　　    人类其实很孤独。在苍茫的天宇中，虽然有亿万星辰，但是却很难寻到第二颗生命源星。\n　　\n　　    不过人类从来没有放弃过探索，自上世纪以来已经发射诸多太空探测器。\n　　\n　　    旅行者二号是一艘无人外太空探测器，于一九七七年在美国肯尼迪航天中心发射升空。\n　　\n　　    它上面携带着一张主题为“向宇宙致意”的镀金唱片，里面包含一些流行音乐和用地球五十五种语言录制的问候辞，以冀有一天被可能存在的外星文明拦截和收到。\n　　\n　　    从上世纪七十年代到现在，旅行者二号一直在孤独的旅行，在茫茫宇宙中它如一粒尘埃般渺小。\n　　\n　　    同时代的外太空探测器，大多或已经发生故障，或已经中断讯号联系，永远的消失在了枯寂的宇宙中。\n　　\n　　    三十几年过去了，科技在不断发展，人类已经研制出更加先进的外太空探测器，也许不久的将来对星空的探索会取得更进一步的发展。\n　　\n　　    但纵然如此，在相当长的时间内，新型外太空探测器依然无法追上旅行者二号的步伐。\n　　\n　　    三十三年过去了，旅行者二号距离地球已经有一百四十亿公里。\n　　\n　　    此时此刻，它已经达到第三宇宙速度，轨道再也不能引导其飞返太阳系，成为了一艘星际太空船。\n　　\n　　    黑暗与冰冷的宇宙中，星辰点点，犹如一颗颗晶莹的钻石镶嵌在黑幕上。\n　　\n　　    旅行者二号太空探测器虽然正在极速飞行，但是在幽冷与无垠的宇宙中却像是一只小小的蚁虫在黑暗的大地上缓缓爬行。\n　　\n　　    三十多年过去后，就在今日这一刻，旅行者二号有了惊人的发现！\n　　\n　　    在枯寂的宇宙中，九具庞大的尸体静静的横在那里……\n　　\n　　    二零一零年五月二十二日，美国宇航局接收到旅行者二号传送回的一组神秘的数据信息，经过艰难的破译与还原，他们看到了一幅不可思议的画面。\n　　\n　　    在这一刻宇航局主监控室内所有人同时变色，最后如木雕泥塑般一动不动，他们震惊到了无以复加的地步！\n　　\n　　    直至过了很久众人才回过神来，而后主监控室内一下子沸腾了。\n　　\n　　    “上帝，我看到了什么？”\n　　\n　　    “这怎么可能，无法相信！”\n　　\n　　    ……\n　　\n　　    旅行者二号早已不受引导，只能单一的前进，传送回这组神秘的数据信息后，在那片漆黑的宇宙空间匆匆而过，驶向更加幽暗与深远的星域。\n　　\n　　    由于那片星空太遥远，纵然有了重大发现，捕捉到了一幅震撼性的画面，人类目前也无能为力。\n　　\n　　    这组神秘信息并没有对外公布。而不久后，旅行者二号发生了故障，中断了与地球的讯号传送。\n　　\n　　    也许至此可以画上一个句号了，不过有时候事情往往会出乎人们的预料。\n　　\n　　    无论是对星空的观测与探索，还是进行生命与物理的科学研究，空间站都具有得天独厚的优越环境。\n　　\n　　    从一九七一年苏联首先发射载人空间站成功，到目前为止全世界已发射了九个空间站。\n　　\n　　    二零一零年六月十一日，此时此刻，绕地而行的国际空间站内，几名宇航员同时变了颜色，瞳孔急骤收缩。\n　　\n　　    时至今日，神的存在，早已被否定。如果还有人继续信仰，那也只是因心灵空虚而寻找一份寄托而已。\n　　\n　　    但是就在这一刻，几名宇航精英的思想受到了强烈的的冲击，他们看到了一幅不可思议的画面。\n　　\n　　    在国际空间站外，冰冷与黑暗并存的宇宙中，九条庞然大物一动不动，仿佛亘古就已横在那里，让人感觉到无尽的苍凉与久远，那竟然是九具龙尸！\n　　\n　　    与古代神话传说中的龙一般无二。\n　　\n　　    每具龙尸都长达百米，犹如铁水浇铸而成，充满了震撼性的力感。\n　　\n　　    九具龙尸皆是五爪黑龙，除却龙角晶莹剔透、紫光闪闪外，龙身通体呈黑色，乌光烁烁，鳞片在黑暗中闪烁着点点神秘的光华。\n　　\n　　    龙，传说中的存在，与神并立，凌驾于自然规律之上。但是，科学发展到现在，还有谁会相信龙真的存在？\n　　\n　　    国际空间站内的几名宇航员，思想受到了强烈的冲击，眼前所见让他们感觉不可思议！\n　　\n　　    枯寂的宇宙中，冰冷的龙尸似不可摧毁的钢铁长城，甚至能够感觉到尸身中所蕴含的恐怖力量。\n　　\n　　    只是，它们已经失去了生气，永远的安息在了幽冷的宇宙空间中。\n　　\n　　    “那是……”\n　　\n　　    被深深震撼过后，几名宇航精英的瞳孔再次急骤收缩，他们看到了更为让人震惊的画面。\n　　\n　　    九具龙尸都长达百米，在尾端皆绑缚着碗口粗的黑色铁索，连向九具龙尸身后那片黑暗的宇宙空间，在那里静静的悬着一口长达二十米的青铜棺椁。\n　　\n　　    巨索千锤百炼，粗长而又坚固，点点乌光令它显得阴寒无比。\n　　\n　　    青铜巨棺古朴无华，上面有一些模糊的古老图案，充满了岁月的沧桑感，也不知道在宇宙中漂浮多少年了。\n　　\n　　    九龙拉棺！\n　　\n　　    在这漆黑而又冰冷的宇宙中，九具龙尸与青铜巨棺被粗长的黑色铁索连在一起，显得极其震撼。\n　　\n　　    在面对那不可思议的监控画面一阵失神后，几名宇航精英第一时间发出了呼叫讯号。\n　　\n　　    “呼叫地球……”\n　　\n　　    ※※※※※\n　　\n　　    关于旅行者二号确实存在，上世纪七十年代在美国发射升空，2010年四五月份与地球失去联系。\n　　\n　　    辰东的新书开始上传，距离上本老书结束已经有了一段相当长的空白期，现在回来了，请各位书友多多支持下，感谢！\n　　\n　　    现在需要登录起点帐号，点击才有效，不然点击不计算在内，请给位兄弟姐妹们辛苦下。\n　　\n　　    新书需要点击、收藏、推荐票，请书友们支持下新书。\n　　\n　　    晚上会接着更新的。\n　　\n　　    (看小说到)16977小游戏每天更新好玩的小游戏，等你来发现！\r\n\n\r\n\n\r\nPs:书友们，我是辰东，推荐一款免费小说App，支持小说下载、听书、零广告、多种阅读模式。请您关注微信公众号：dazhuzaiyuedu（长按三秒复制）书友们快关注起来吧！\r\n\n\r\n\n\r\n\t\t\t\t";
        s = s.replaceAll("　"," ")
                .replaceAll("\\t","    ")
                .replaceAll(" {2,}","  ")
                .replaceAll(" +\n","\n")
                .replaceAll("\n+","\n\n");

        System.out.println(s.substring(1,100));
    }
}
