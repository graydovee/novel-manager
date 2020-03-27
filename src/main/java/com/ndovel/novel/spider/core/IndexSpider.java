package com.ndovel.novel.spider.core;

import com.ndovel.novel.model.dto.TempBook;
import com.ndovel.novel.model.dto.TempChapter;

import java.util.List;

public interface IndexSpider {

    @Deprecated
    List<TempChapter> getIndex(String url);

    TempBook getTempBook(String url);
}
