package com.ndovel.ebook.spider.core;

import com.ndovel.ebook.model.dto.TempBook;
import com.ndovel.ebook.model.dto.TempChapter;

import java.util.List;

public interface IndexSpider {

    List<TempChapter> getIndex(String url);

    TempBook getTempBook(String url);
}
