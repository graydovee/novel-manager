package com.ndovel.ebook;

import com.ndovel.ebook.model.dto.SearchResult;
import com.ndovel.ebook.model.dto.TempBook;
import com.ndovel.ebook.spider.core.IndexSpider;
import com.ndovel.ebook.spider.core.SearchSpider;
import com.ndovel.ebook.spider.core.impl.IndexSpiderImpl;
import com.ndovel.ebook.spider.core.impl.SearchSpiderImpl;
import org.junit.Test;

import java.util.List;

public class SpiderTests {

    @Test
    public void indexSpiderTest(){
        String url = "http://www.biquge.tv/2_2491/";
        IndexSpider spider = new IndexSpiderImpl();
        TempBook tempBook = spider.getTempBook(url);
        System.out.println(tempBook);
    }

    @Test
    public void searchSpiderTest(){
        SearchSpider spider = new SearchSpiderImpl();
        List<SearchResult> books = spider.search("斗破苍穹");
        System.out.println(books);
    }
}
