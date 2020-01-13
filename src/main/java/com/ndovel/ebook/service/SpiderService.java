package com.ndovel.ebook.service;

import com.ndovel.ebook.exception.RequestException;
import com.ndovel.ebook.model.dto.*;

import java.io.IOException;
import java.util.List;


public interface SpiderService {

    BookDTO spider(SpiderIndex spiderIndex);

    TempChapter spiderOne(String url, Integer matchRexId);

    SpiderInfoDTO update(Integer SpiderInfoId);

    List<TempChapter> spiderByName(String name);

    TempBook spiderByIndex(String url);

    void saveImg(String imgUrl, String imgName) throws RequestException, IOException;
}
