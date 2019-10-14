package com.ndovel.ebook.spider.core;

import com.ndovel.ebook.model.dto.SpiderInfoDTO;

public interface IndexSpider {

    SpiderInfoDTO makeSpiderInfo(String url);
}
