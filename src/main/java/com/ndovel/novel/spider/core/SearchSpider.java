package com.ndovel.novel.spider.core;


import com.ndovel.novel.model.dto.SearchResult;

import java.util.List;

public interface SearchSpider {

    List<SearchResult> search(String key);
}
