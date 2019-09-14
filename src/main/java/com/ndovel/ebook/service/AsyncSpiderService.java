package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.spider.core.NovelSpider;


public interface AsyncSpiderService {

    BookDTO spider(BookDTO bookDTO, String url, String encode, Integer matchRexDTOId);

    void down(Book book, NovelSpider spider, Boolean flag);

    default void down(Book book, NovelSpider spider){
        down(book,spider,false);
    }
}
