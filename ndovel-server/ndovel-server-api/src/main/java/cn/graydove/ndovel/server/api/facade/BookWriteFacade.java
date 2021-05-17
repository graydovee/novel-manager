package cn.graydove.ndovel.server.api.facade;

import cn.graydove.ndovel.server.api.model.request.*;

/**
 * @author graydove
 */
public interface BookWriteFacade {

    Long createBook(BookRequest bookRequest);

    Long appendChapter(ChapterRequest chapterRequest);

    Boolean updateBook(UpdateBookRequest updateBookRequest);

    Boolean deleteBook(BookDeleteRequest bookDeleteRequest);

    Boolean updateChapter(UpdateChapterRequest updateChapterRequest);
}
