package com.ndovel.novel.spider.core.impl;

import com.ndovel.novel.model.dto.SearchResult;
import com.ndovel.novel.spider.core.SearchSpider;
import com.ndovel.novel.spider.remote.model.Response;
import com.ndovel.novel.spider.remote.service.SpiderHttpService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author graydove
 */
public class RemoteSearchSpider implements SearchSpider {

    private SpiderHttpService spiderHttpService;

    public RemoteSearchSpider(SpiderHttpService spiderHttpService) {
        this.spiderHttpService = spiderHttpService;
    }

    @Override
    public List<SearchResult> search(String key) {
        return Optional.ofNullable(spiderHttpService.search(key, null))
                .map(Response::getData)
                .map(com.ndovel.novel.spider.remote.model.SearchResult::getResult)
                .map(list -> list.stream().map(textLink -> {
                    SearchResult searchResult = new SearchResult();
                    searchResult.setBookName(textLink.getName());
                    searchResult.setUrl(textLink.getUrl());
                    return searchResult;
                }).collect(Collectors.toList())).orElse(Collections.emptyList());
    }
}
