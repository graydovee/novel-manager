package com.ndovel.novel.spider.core;

import com.ndovel.novel.exception.RequestException;
import com.ndovel.novel.spider.util.HttpClientUtils;
import com.ndovel.novel.spider.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractSpider {

    private String getFullPath(String url) {
        return UrlUtils.urlFormat(url);
    }

    protected String sendHttpGetRequest(String url) {
        try {
            return HttpClientUtils.get(getFullPath(url));
        } catch (RequestException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
