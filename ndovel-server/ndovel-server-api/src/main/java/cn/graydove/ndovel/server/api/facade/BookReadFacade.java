package cn.graydove.ndovel.server.api.facade;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.model.request.*;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;

/**
 * @author graydove
 */
public interface BookReadFacade {

    ChapterVO findChapter(ChapterIdRequest chapterIdRequest);

    Paging<ChapterVO> pageChapter(ChapterPageRequest chapterPageRequest);

    Paging<ChapterVO> pageAllChapter(ChapterPageAllRequest chapterPageAllRequest);

    Paging<BookVO> pageBook(BookPageRequest bookPageRequest);

    BookVO findBook(BookIdRequest bookIdRequest);
}
