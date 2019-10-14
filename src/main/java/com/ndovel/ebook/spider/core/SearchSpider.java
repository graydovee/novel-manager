package com.ndovel.ebook.spider.core;


import com.ndovel.ebook.model.dto.SpiderIndex;

import java.util.List;

public interface SearchSpider {

    List<SpiderIndex> findAllIndex(String key);
}
