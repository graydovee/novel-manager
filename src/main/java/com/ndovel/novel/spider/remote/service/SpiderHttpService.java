package com.ndovel.novel.spider.remote.service;

import cn.graydove.httpmaster.starter.annotation.HttpService;
import cn.graydove.httpmaster.starter.annotation.method.HttpGet;
import com.ndovel.novel.spider.remote.model.NovelIndex;
import com.ndovel.novel.spider.remote.model.Response;
import com.ndovel.novel.spider.remote.model.SearchResult;

/**
 * @author graydove
 */
@HttpService(url = "http://localhost:18083")
public interface SpiderHttpService {

    @HttpGet(path = "search")
    ResponseSearchResult search(String name, String url);

    @HttpGet(path = "index")
    ResponseNovelIndex index(String url);

    class ResponseNovelIndex extends Response<NovelIndex> {
    }

    class ResponseSearchResult extends Response<SearchResult> {
    }
}
