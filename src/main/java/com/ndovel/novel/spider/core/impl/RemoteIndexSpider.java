package com.ndovel.novel.spider.core.impl;

import com.ndovel.novel.model.dto.TempBook;
import com.ndovel.novel.model.dto.TempChapter;
import com.ndovel.novel.spider.core.IndexSpider;
import com.ndovel.novel.spider.remote.model.NovelIndex;
import com.ndovel.novel.spider.remote.model.Response;
import com.ndovel.novel.spider.remote.service.SpiderHttpService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author graydove
 */
public class RemoteIndexSpider implements IndexSpider {

    private SpiderHttpService spiderHttpService;

    public RemoteIndexSpider(SpiderHttpService spiderHttpService) {
        this.spiderHttpService = spiderHttpService;
    }

    @Override
    public List<TempChapter> getIndex(String url) {
        return null;
    }

    @Override
    public TempBook getTempBook(String url) {
        SpiderHttpService.ResponseNovelIndex response = spiderHttpService.index(url);
        if (response.getCode() == 200) {
            NovelIndex index = response.getData();
            TempBook tempBook = new TempBook();
            tempBook.setCoverUrl(index.getCover());
            tempBook.setAuthorName(index.getAuthor());
            tempBook.setBookName(index.getTitle());
            tempBook.setIntroduce(index.getIntroduce());
            tempBook.setChapters(index.getChapterList().stream().map(textLink -> {
                TempChapter tempChapter = new TempChapter();
                tempChapter.setTitle(textLink.getName());
                tempChapter.setUrl(textLink.getUrl());
                return tempChapter;
            }).collect(Collectors.toList()));
            return tempBook;
        }
        return null;
    }
}
