package cn.graydove.ndovel.server.api.facade;

import cn.graydove.ndovel.server.api.model.request.BookRequest;
import cn.graydove.ndovel.server.api.model.request.ChapterRequest;
import cn.graydove.ndovel.server.api.model.request.UpdateBookRequest;
import cn.graydove.ndovel.server.api.model.request.UpdateChapterRequest;

/**
 * @author graydove
 */
public interface BookWriteFacade {

    Long createBook(BookRequest bookRequest);

    Long appendChapter(ChapterRequest chapterRequest);

    Boolean updateBook(UpdateBookRequest updateBookRequest);

    Boolean updateChapter(UpdateChapterRequest updateChapterRequest);
}
