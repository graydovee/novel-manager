package com.ndovel.ebook.spider.core;


import com.ndovel.ebook.model.dto.SearchResult;
import com.ndovel.ebook.model.dto.TempChapter;

import java.util.List;

public interface SearchSpider {

    List<SearchResult> search(String key);
}
