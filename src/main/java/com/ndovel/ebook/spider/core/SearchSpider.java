package com.ndovel.ebook.spider.core;


import com.ndovel.ebook.model.dto.TempBook;

import java.util.List;

public interface SearchSpider {

    List<TempBook> findAllIndex(String key);
}
