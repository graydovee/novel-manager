package com.ndovel.ebook.spider.core;


import com.ndovel.ebook.spider.pojo.SpiderIndex;

import java.util.List;

public interface SearchSpider {

    List<SpiderIndex> findAllIndex(String key);
}
