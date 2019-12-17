package com.ndovel.ebook.spider.core;

import com.ndovel.ebook.exception.RequestException;
import com.ndovel.ebook.spider.util.HttpClientUtils;
import com.ndovel.ebook.spider.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractSpider {
    private final static int RETRY_TIMES = 3;
    private final static int RETRY_INTERVAL = 1;

    private String getFullPath(String url) {
        return UrlUtils.urlFormat(url);
    }

    protected String sendHttpGetRequest(String url) {
        for (int i = 1; i <= RETRY_TIMES; ++i) {
            try {
                return HttpClientUtils.get(getFullPath(url));
            } catch (RequestException e) {
                if (i < 3) {
                    log.warn("the " + i + "th retry will started after " + RETRY_INTERVAL +"s");
                    try {
                        Thread.sleep(RETRY_INTERVAL * 1000);
                    } catch (InterruptedException ex) {
                        log.error("sleep error");
                    }
                } else {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }
}
