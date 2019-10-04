package com.ndovel.ebook.service;

import com.ndovel.ebook.model.entity.SpiderInfo;

import java.util.List;

public interface SpiderInfoService {

    List<SpiderInfo> findAll();

    SpiderInfo delete(Integer id);

    SpiderInfo refresh(Integer id);

    SpiderInfo save(Integer id, String url, Integer matchRexId);
}
