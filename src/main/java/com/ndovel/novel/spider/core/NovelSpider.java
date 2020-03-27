package com.ndovel.novel.spider.core;

import com.ndovel.novel.model.dto.ChapterDTO;
import com.ndovel.novel.model.dto.ContentDTO;
import com.ndovel.novel.model.dto.TempChapter;

public interface NovelSpider {

    boolean hasNext();

    String getUrl();

    /**
     * 爬取下一章节
     */
    void run();

    ContentDTO getContent();

    ChapterDTO getChapter();

    TempChapter getTempChapter();

}
