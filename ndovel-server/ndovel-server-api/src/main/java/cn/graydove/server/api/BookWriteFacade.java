package cn.graydove.server.api;

import cn.graydove.server.model.request.BookRequest;
import cn.graydove.server.model.request.ChapterRequest;

/**
 * @author graydove
 */
public interface BookWriteFacade {

    Long createBook(BookRequest bookRequest);

    Long appendChapter(ChapterRequest chapterRequest);
}
