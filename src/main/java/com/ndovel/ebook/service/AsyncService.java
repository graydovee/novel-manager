package com.ndovel.ebook.service;

import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.spider.core.NovelSpider;

public interface AsyncService {


    void down(Book book, NovelSpider spider, Boolean flag);

    void down(Book book, NovelSpider spider);
}
