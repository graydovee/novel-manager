package com.ndovel.novel.spider.core;

import com.ndovel.novel.spider.util.HttpClientUtils;
import com.ndovel.novel.spider.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractSpider {

    private String getFullPath(String url) {
        return UrlUtils.urlFormat(url);
    }

    protected String sendHttpGetRequest(String url) {
        int c = 1;
        int retryTimes = 3;
        while (c <= retryTimes) {
            try {
                return HttpClientUtils.get(getFullPath(url));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            if (++c <= retryTimes) {
                int n = c - 1;
                log.info("爬取失败，{}秒后重试", n);
                try {
                    Thread.sleep(1000 * n);
                } catch (InterruptedException ignored) {
                }
            }
        }
        return null;
    }
}
