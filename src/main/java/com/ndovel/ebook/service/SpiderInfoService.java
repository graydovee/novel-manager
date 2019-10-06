package com.ndovel.ebook.service;

import com.ndovel.ebook.model.entity.SpiderInfo;

import java.util.List;

public interface SpiderInfoService {

    List<SpiderInfo> findAll();

    List<SpiderInfo> findAllNotFinished();

    Integer finishSpider(Integer id);

    Integer continueSpider(Integer id);

    SpiderInfo save(Integer id, String url, Integer matchRexId);
}
