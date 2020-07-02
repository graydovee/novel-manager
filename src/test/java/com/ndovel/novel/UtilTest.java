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

        spiderInfo.setUrl("https://www.datouxiaw.com/xs/369209/114.html");
        rex.setContentRex("<ins class=\"adsbygoogle\"[\\s\\S]+</script>([\\s\\S]*?)<script async ");
        rex.setNextPageRex("<a onclick=\"javascrtpt:window.location.href='([\\S]+)'\">下一章</a>");
        rex.setTitleRex("<h1><a title=\"([\\s\\S]+?)\"");

        spiderInfo.setMatchRex(rex);

        NovelSpider spider = new CommonNovelSpider(spiderInfo);
        spider.run();
        TempChapter tempChapter = spider.getTempChapter();
        System.out.println(tempChapter);
    }
}
