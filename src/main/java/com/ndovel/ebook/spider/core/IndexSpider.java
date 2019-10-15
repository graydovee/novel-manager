package com.ndovel.ebook.spider.core;

import com.ndovel.ebook.model.dto.TempChapter;

import java.util.List;

public interface IndexSpider {

    List<TempChapter> getIndex(String url);
}
