package cn.graydove.ndovel.spider.service;

import cn.graydove.ndovel.spider.model.dto.*;

/**
 * @author graydove
 */
public interface SpiderService {

    void spider(BookDTO bookDTO);

    Long createBook(BookDTO bookDTO);

    Boolean deleteBook(BookDeleteDTO bookDeleteDTO);

    void createChapter(ChapterDTO chapterDTO);
}
