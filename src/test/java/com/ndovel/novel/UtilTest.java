package com.ndovel.novel;

import com.ndovel.novel.exception.DataIsNotExistException;
import com.ndovel.novel.model.dto.*;
import com.ndovel.novel.model.entity.MatchRex;
import com.ndovel.novel.service.SpiderService;
import com.ndovel.novel.spider.core.IndexSpider;
import com.ndovel.novel.spider.core.NovelSpider;
import com.ndovel.novel.spider.core.SearchSpider;
import com.ndovel.novel.spider.core.impl.CommonNovelSpider;
import com.ndovel.novel.spider.core.impl.IndexSpiderImpl;
import com.ndovel.novel.spider.core.impl.SearchSpiderImpl;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

class UtilTest {

    @Autowired
    SpiderService spiderService;

//    @Test
//    void JsoupTest() {
//        try {
////            Document document = Jsoup.connect("https://www.xinxs.la/ar.php?keyWord=%E4%BB%99%E5%B8%9D%E5%BD%92%E6%9D%A5").get();
//            Document document = Jsoup.connect("https://www.xsbiquge.com/26_26046/").get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    void spiderTest() {
        SearchSpider searchSpider = new SearchSpiderImpl();
        List<SearchResult> index = searchSpider.search("仙帝归来");
        Assertions.assertTrue(index.size() > 0);

        IndexSpider indexSpider = new IndexSpiderImpl();
        TempBook tempBook = indexSpider.getTempBook(index.get(0).getUrl());
        Assertions.assertTrue(StringUtils.isNotEmpty(tempBook.getAuthorName()));
        Assertions.assertTrue(StringUtils.isNotEmpty(tempBook.getBookName()));
        Assertions.assertTrue(StringUtils.isNotEmpty(tempBook.getCoverUrl()));
        Assertions.assertTrue(StringUtils.isNotEmpty(tempBook.getIntroduce()));
        Assertions.assertTrue(tempBook.getChapters().size() > 0);
    }

    @Test
    void spiderContentTest() {
        SpiderInfoDTO spiderInfo = new SpiderInfoDTO();
        MatchRexDTO rex = new MatchRexDTO();

        spiderInfo.setUrl("https://www.xsbiquge.com/84_84521/65326.html");
//        rex.setContentRex("<ins class=\"adsbygoogle\"[\\s\\S]+</script>([\\s\\S]*?)<script async ");
        rex.setContentRex("<div[^<]*id=\"content\"[^<]*>([\\s\\S]*?)</div>");
//        rex.setNextPageRex("<a onclick=\"javascrtpt:window.location.href='([\\S]+)'\">下一章</a>");
        rex.setNextPageRex("<a[^<]*href=\"([^<]*.html)\"[^<]*>下一");
//        rex.setTitleRex("<h1><a title=\"([\\s\\S]+?)\"");
        rex.setTitleRex("<h1>([^<]*)</h1>");

        spiderInfo.setMatchRex(rex);

        NovelSpider spider = new CommonNovelSpider(spiderInfo);
        spider.run();
        TempChapter tempChapter = spider.getTempChapter();
        System.out.println(tempChapter);
    }

    @Test
    void testStringFormat() {
        String s = "<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;气血值：25/101。（状态/上限）<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;精力值：10/50。（状态/上限）<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;隐藏天赋：铁19%，铜9%，锡3%，银1%，金0%，磷1%，硫1%，硅8%……（天赋比例越高，代表与能源的亲和度越高。）<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;特殊能力：无。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;进化：1/5。（完成度）<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;系统检测结果：宿主颜值（完美），气血值（普通），精力值（普通），隐藏天赋（低等）。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“隐藏天赋……低等？你确定不是搞错对象了？”高能可以容忍说他颜值完美，也可以容忍说他气血值和精力值普通，但隐藏天赋“低等”，这个他忍不了。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;虽然，他还是第一次看到自己的隐藏天赋数值，可那些数字代表的意义，他却非常清楚。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;隐藏天赋里的数字，就是一个人对特定能源的亲和度。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;亲和度越高，控制力越强。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;就像秦天雄在台上说的，他对铁能源的提取量是百分之五十七，其实，代表的意思就是秦天雄的铁能源亲和度为57%。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;而沈凝儿能在秦天雄提取后进行二次提取，意思也很明显，就是沈凝儿的铁能源亲和度最少在80%，甚至90%以上。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;那么，问题就来了。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;他隐藏天赋里面这个铁能源19%是什么鬼？！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“叮，宿主任务完成难度，优秀！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“叮，宿主将获得最强能力的洗礼，并且，100%机率获赠初级宝箱，50%机率随机获得特殊能力。”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;正准备骂人，高能就再次听到一连串的声音响起。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“最强能力洗礼开启！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“获得献祭对象‘沈凝儿’的隐藏天赋。”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“开启隐藏天赋分析。”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“分析中……”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“结论：沈凝儿拥有超一流的隐藏天赋。”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“开始复制沈凝儿的隐藏天赋。”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“复制中……”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“复制成功！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“开始加强沈凝儿（复制版）的隐藏天赋。”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“加强中……”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“加强成功！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“刷新隐藏天赋值！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“刷新隐藏天赋值？！”高能原本还听得一愣一愣的，可是，在听到最后一句话的时候，他的眼睛是真的一下就瞪圆了，因为，在声音响起的同时，他就看到自己隐藏天赋值的数字开始飞快的翻滚起来。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;铁25%……<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;铁39%……<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;铁55%……<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;……<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;不止是铁的数值在翻滚，其它的，铜，锡，银，磷，硫，硅……等等都开始翻滚，连之前显示0%的金都开始飞速提升。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;说得很慢，但其实，中间也就是一两秒的时间。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;瞬间，隐藏天赋的数字全部定格。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;新的数值出现：铁100%，铜100%，锡100%，银100%，金100%，磷100%，硫100%，硅100%……<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“……”高能呆住了。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;全满了？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;卧糟，居然全满了？！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;他并不是那种得了便宜还卖乖的人，可是，此刻此刻，他真的很想发自内心的说一句，要不要这么刺激？其实，我没想要这么强的啊！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;隐藏天赋……<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;全满啊！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;我的娘？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;有没有这么夸张？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;高能是真的被吓到了，这劳什子玩意儿出的任务变态，可是，获得的奖励也同样是超级变态。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;是因为献祭了沈凝儿，所以，隐藏天赋被刷新了？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;又或者还有什么别的功能？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;高能现在还无法确定，但是，他的目光却被另外一个地方吸引住了。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;等一下！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;这个属性面板最后的那个进化1/5是什么东西？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;高能是一个学霸，所以他的观察力一向很细腻，很少放过细节，而属性面板前面的他都可以大概理解，只是，那个进化1/5他就有些无法判断了，后面的解释是完成度？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;是不是代表完成5/5就可以进化？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;那要怎么完成？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;完成一个任务加“1”点吗？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;这似乎是一种可能。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;但最主要的是，这个进化完成后，会进化成什么？变身成超人？还是变形金刚？或者是进化出一块随身田？甚至是天降暴走小萝莉？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;高能的脑子里面乱七八糟的想着。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;他的知识面其实是非常广泛的，作为学霸，他经常读书，还喜欢看小说，最喜欢的就是超级帅的小说作者“薪意”，那本《神门》堪称经典中的经典。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;所以，在无意识间，他对于小萝莉也有了一点执念。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“叮，获赠初级宝箱一只！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“叮，50%机率随机获得特殊能力。”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“幸运大转盘，启动！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;正想着，高能就看到面前出现了一个小木箱，同时，还有一个看不清上面写着什么的金色大转盘。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“叮，因为宿主颜值完美，幸运之神对你产生眷顾，获得特殊能力：第三套中小学广播体操（舞动青春）。”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“友情提示：当宿主跳第三套中小学广播体操动作时，将会获得‘心情愉悦’状态，在这种状态下，修炼速度提升50%，气血恢复速度提升300%，不良状态消除。”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“什么广播……什么体操？还舞动青春？这么牛逼的第三套中小学广播体操，我能不能不要？！”高能有点懵。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;试想一下，别人修炼和恢复气血都是磕药或者静坐吐气，他却得不停的跳广播体操？甚至于还有可能在打架的时候，打着打着，他就开始跳广播体操……<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;画面太美！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;太羞耻！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;高能即使不是一般人，此刻也有点适应不了。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;不过，现在倒不是计较这个的时候，因为，他现在的状态就奇差，不管效果如何，他都必须要试试了。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;至于初级宝箱……<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;日后再说！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;高能强行爆发，用尽全部力气伸出了双手，第三套中小学广播体操（舞动青春），第一节，伸展运动，趴着版……开始！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“啪！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;好像扇中什么东西？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;高能很意外。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;而阿四则是捂着他，一脸的懵逼。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;他原本看到高能瘫在了沈凝儿身上，根本就没有想过还能动，一个不留意就被高能的一个伸展动运一巴掌扇在脸上。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;火辣辣的疼。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;而高能在完成了第一个伸展运动后，整个就像是打了鸡血一样，居然一下就弹了起来，口里还有序的喊着节拍：“一二三四，二二三四……”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“？？？”阿四呆住了。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“！！！”唐巍巍同样瞪圆了眼睛。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;第三高级中学的学霸高能，被迷魂香给弄神经了？<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“按住他，高能八成被吓成神经病了！”唐巍巍是有实力的，他可不比阿四，虽然，他一般情况下都在玩智商，可真动起手来，却也担得起虎虎生风。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;一边招呼阿四的同时，他也冲到了高能的面前，身体一低，躲过一记伸展运动，接着，就是一记下勾拳。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;高能的脸有些微红。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;真是羞耻啊……<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;不过，还别说，这广播体操是真好用，一节伸展运动下来，他就看到原本只剩下25的气血值恢复到了50，精力值也从10恢复到了21。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;简直比磕药还要猛！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;最主要的是，他的心情真的变得很愉悦，脑瓜子都清醒了很多，黑夜中，他的眼睛就像鹰一样炯炯有神。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;第三节，踢腿运动！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“咚！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;一脚正中唐巍巍的脑门。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;第五节，体转运动！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“啪！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;阿四被一记肘击，撞飞。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;很嗨啊！<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;高能正准备再跳一会儿，就发现床上的沈凝儿似乎动了一下，眉头微皱，感觉随时都可能要醒过来。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;不止如此，在门外还有着一阵紧密的脚步声传来。<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;“咚咚咚！”<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;房门发出沉重的响声。";
        String s1 = com.ndovel.novel.utils.StringUtils.formatContent(s);
        System.out.println(s1);
    }
}
