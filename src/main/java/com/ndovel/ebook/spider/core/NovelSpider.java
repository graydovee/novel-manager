package com.ndovel.ebook.spider.core;

import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;
import com.ndovel.ebook.model.dto.TempChapter;

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
