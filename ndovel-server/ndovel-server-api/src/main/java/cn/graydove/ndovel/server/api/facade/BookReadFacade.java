package cn.graydove.ndovel.server.api.facade;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.model.request.BookIdRequest;
import cn.graydove.ndovel.server.api.model.request.BookPageRequest;
import cn.graydove.ndovel.server.api.model.request.ChapterIdRequest;
import cn.graydove.ndovel.server.api.model.request.ChapterPageRequest;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;

/**
 * @author graydove
 */
public interface BookReadFacade {

    ChapterVO findChapter(ChapterIdRequest chapterIdRequest);

    Paging<ChapterVO> pageChapter(ChapterPageRequest chapterPageRequest);

    Paging<BookVO> pageBook(BookPageRequest bookPageRequest);

    BookVO findBook(BookIdRequest bookIdRequest);
}
