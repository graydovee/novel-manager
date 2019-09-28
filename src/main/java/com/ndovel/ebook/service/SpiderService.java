package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.BookDTO;

import java.util.Map;


public interface SpiderService {

    BookDTO spider(BookDTO bookDTO, String url, Integer matchRexDTOId);

    Map<String, String> spiderOne(String url, Integer matchRexDTOId);

}
