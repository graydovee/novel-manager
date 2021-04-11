package cn.graydove.ndovel.server.api.facade;

import cn.graydove.ndovel.server.api.model.request.BookRequest;
import cn.graydove.ndovel.server.api.model.request.ChapterRequest;

/**
 * @author graydove
 */
public interface BookWriteFacade {

    Long createBook(BookRequest bookRequest);

    Long appendChapter(ChapterRequest chapterRequest);
}
