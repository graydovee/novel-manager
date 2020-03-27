package com.ndovel.novel.service;

import com.ndovel.novel.model.entity.SpiderInfo;

public interface AsyncService {


    void down(SpiderInfo spiderInfo, Boolean isNotFist);

    void down(SpiderInfo spiderInfo);
}
