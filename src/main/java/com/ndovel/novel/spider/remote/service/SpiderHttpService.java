package com.ndovel.novel.spider.remote.service;

import cn.graydove.httpmaster.starter.annotation.HttpService;
import cn.graydove.httpmaster.starter.annotation.method.HttpGet;
import com.ndovel.novel.spider.remote.model.NovelChapter;
import com.ndovel.novel.spider.remote.model.NovelIndex;
import com.ndovel.novel.spider.remote.model.Response;
import com.ndovel.novel.spider.remote.model.SearchResult;

/**
 * @author graydove
 */
@HttpService(url = "http://localhost:18083")
public interface SpiderHttpService {

    @HttpGet(path = "search")
    Response<SearchResult> search(String name, String url);

    @HttpGet(path = "index")
    Response<NovelIndex> index(String url);

    @HttpGet(path = "chapter")
    Response<NovelChapter> chapter(String url);
}
