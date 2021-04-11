package cn.graydove.ndovel.server.api;

import cn.graydove.ndovel.server.model.request.BookRequest;
import cn.graydove.ndovel.server.model.request.ChapterRequest;

/**
 * @author graydove
 */
public interface BookWriteFacade {

    Long createBook(BookRequest bookRequest);

    Long appendChapter(ChapterRequest chapterRequest);
}
