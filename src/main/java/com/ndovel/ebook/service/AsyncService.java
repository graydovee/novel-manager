package com.ndovel.ebook.service;

import com.ndovel.ebook.model.entity.SpiderInfo;

public interface AsyncService {


    void down(SpiderInfo spiderInfo, Boolean isNotFist);

    void down(SpiderInfo spiderInfo);
}
