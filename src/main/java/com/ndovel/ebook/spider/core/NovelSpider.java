package com.ndovel.ebook.spider.core;

import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;

public interface NovelSpider {

    boolean hasNext();

    /**
     * 爬取下一章节
     */
    void run();

    void update();

    ContentDTO getContent();

    ChapterDTO getChapter();

}
