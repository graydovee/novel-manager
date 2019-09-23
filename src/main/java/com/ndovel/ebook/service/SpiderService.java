package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.spider.core.NovelSpider;

import java.util.Map;


public interface SpiderService {

    BookDTO spider(BookDTO bookDTO, String url, Integer matchRexDTOId);

    Map<String, String> spiderOne(String url, Integer matchRexDTOId);

}
