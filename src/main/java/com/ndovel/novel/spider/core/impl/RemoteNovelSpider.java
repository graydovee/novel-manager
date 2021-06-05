package com.ndovel.novel.spider.core.impl;

import com.ndovel.novel.model.dto.BookDTO;
import com.ndovel.novel.model.dto.SpiderInfoDTO;
import com.ndovel.novel.spider.core.AbstractNovelSpider;
import com.ndovel.novel.spider.remote.model.NovelChapter;
import com.ndovel.novel.spider.remote.model.Response;
import com.ndovel.novel.spider.remote.service.SpiderHttpService;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author graydove
 */
public class RemoteNovelSpider extends AbstractNovelSpider {

    private SpiderHttpService spiderHttpService;

    private Map<String, NovelChapter> cache = new ConcurrentHashMap<>();

    public RemoteNovelSpider(SpiderInfoDTO spiderInfoDTO, SpiderHttpService spiderHttpService) {
        super(Optional.ofNullable(spiderInfoDTO.getBook()).map(BookDTO::getId).orElse(null), spiderInfoDTO.getUrl());
        this.spiderHttpService = spiderHttpService;
    }

    @Override
    protected String sendHttpGetRequest(String url) {
        if (url == null) {
            return null;
        }
        Response<NovelChapter> response = spiderHttpService.chapter(url);
        NovelChapter chapter = null;
        if (response != null && response.getCode() == 200) {
            chapter = response.getData();
        }
        if (chapter != null) {
            cache.put(url, chapter);
            return url;
        }
        return null;
    }

    @Override
    protected String getContentFormCode(String code) {
        NovelChapter chapter = cache.get(code);
        return chapter.getContent();
    }

    @Override
    protected String getTitleFormCode(String code) {
        NovelChapter chapter = cache.get(code);
        return chapter.getTitle();
    }

    @Override
    protected String getNextPageFormCode(String code) {
        NovelChapter chapter = cache.get(code);
        return chapter.getNextUrl();
    }

}
