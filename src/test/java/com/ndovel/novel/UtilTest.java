package com.ndovel.novel;

import com.ndovel.novel.model.dto.SearchResult;
import com.ndovel.novel.model.dto.TempBook;
import com.ndovel.novel.spider.core.IndexSpider;
import com.ndovel.novel.spider.core.SearchSpider;
import com.ndovel.novel.spider.core.impl.IndexSpiderImpl;
import com.ndovel.novel.spider.core.impl.SearchSpiderImpl;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class UtilTest {

    @Test
    void JsoupTest() {
        try {
//            Document document = Jsoup.connect("https://www.xinxs.la/ar.php?keyWord=%E4%BB%99%E5%B8%9D%E5%BD%92%E6%9D%A5").get();
            Document document = Jsoup.connect("https://www.xsbiquge.com/26_26046/").get();
            System.out.println(document.text());;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
}
