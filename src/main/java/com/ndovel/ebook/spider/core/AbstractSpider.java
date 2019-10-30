package com.ndovel.ebook.spider.core;

import com.ndovel.ebook.exception.RequestException;
import com.ndovel.ebook.spider.util.HttpClientUtils;
import com.ndovel.ebook.spider.util.UrlUtils;
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
            return null;
        }
    }
}
