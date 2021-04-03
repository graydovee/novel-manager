package cn.graydove.server.api;

import cn.graydove.server.model.request.BookRequest;
import cn.graydove.server.model.request.ChapterRequest;
import cn.graydove.server.model.vo.ChapterVO;

/**
 * @author graydove
 */
public interface BookFacade {

    Long createBook(BookRequest bookRequest);

    ChapterVO findChapter(Long chapterId);

    Long appendChapter(ChapterRequest chapterRequest);
}
