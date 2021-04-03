package cn.graydove.ndovel.spider.service;

import cn.graydove.ndovel.spider.model.dto.BookDTO;
import cn.graydove.server.model.vo.ChapterVO;

/**
 * @author graydove
 */
public interface SpiderService {

    void spider(BookDTO bookDTO);

    ChapterVO findChapter(Long chapterId);
}
