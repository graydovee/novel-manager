package com.ndovel.ebook.service;

import com.ndovel.ebook.model.entity.SpiderInfo;

import java.util.List;
import java.util.Optional;

public interface SpiderInfoService {

    List<SpiderInfo> findAll();

    List<SpiderInfo> findAllNotFinished();

    Optional<SpiderInfo> findIsExist(Integer id);

    Integer finishSpider(Integer id);

    Integer continueSpider(Integer id);

    SpiderInfo save(Integer id, String url, Integer matchRexId);
}
