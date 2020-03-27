package com.ndovel.novel.service;

import com.ndovel.novel.exception.RequestException;
import com.ndovel.novel.model.dto.*;

import java.io.IOException;
import java.util.List;


public interface SpiderService {

    BookDTO spider(SpiderIndex spiderIndex);

    TempChapter spiderOne(String url, Integer matchRexId);

    SpiderInfoDTO update(Integer SpiderInfoId);

    List<SearchResult> spiderByName(String name);

    TempBook spiderByIndex(String url);

    void saveImg(String imgUrl, String imgName) throws RequestException, IOException;
}
