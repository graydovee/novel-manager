package com.ndovel.ebook;

import com.ndovel.ebook.model.dto.TempBook;
import com.ndovel.ebook.spider.core.IndexSpider;
import com.ndovel.ebook.spider.core.impl.IndexSpiderImpl;
import org.junit.Test;

public class SpiderTests {

    @Test
    public void indexSpiderTest(){
        String url = "http://www.biquge.tv/2_2491/";
        IndexSpider spider = new IndexSpiderImpl();
        TempBook tempBook = spider.getTempBook(url);
        System.out.println(tempBook);
    }
}
