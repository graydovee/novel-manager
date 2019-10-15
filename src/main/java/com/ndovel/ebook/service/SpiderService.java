package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.*;

import java.util.List;


public interface SpiderService {

    BookDTO spider(String bookName, String authorName, String url, Integer matchRexDTOId);

    TempChapter spiderOne(String url, Integer matchRexId);

    TempChapter spiderOne(TempChapter tempChapter);

    List<SpiderIndex> spiderByName(String name);

    List<TempChapter> spiderByIndex(String url);
}
