package cn.graydove.ndovel.server.center.facade;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.facade.BookReadFacade;
import cn.graydove.ndovel.server.api.model.request.BookIdRequest;
import cn.graydove.ndovel.server.api.model.request.BookPageRequest;
import cn.graydove.ndovel.server.api.model.request.ChapterIdRequest;
import cn.graydove.ndovel.server.api.model.request.ChapterPageRequest;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import cn.graydove.ndovel.server.center.service.BookService;
import cn.graydove.ndovel.server.center.service.ChapterService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author graydove
 */
@DubboService
@AllArgsConstructor
public class BookReadFacadeImpl implements BookReadFacade {

    private ChapterService chapterService;

    private BookService bookService;

    @Override
    public ChapterVO findChapter(ChapterIdRequest chapterIdRequest) {
        return chapterService.findDetail(chapterIdRequest);
    }

    @Override
    public Paging<ChapterVO> pageChapter(ChapterPageRequest chapterPageRequest) {
        return chapterService.pageByBookId(chapterPageRequest);
    }

    @Override
    public Paging<BookVO> pageBook(BookPageRequest bookPageRequest) {
        return bookService.pageBook(bookPageRequest);
    }

    @Override
    public BookVO findBook(BookIdRequest bookIdRequest) {
        return bookService.findBook(bookIdRequest);
    }
}
