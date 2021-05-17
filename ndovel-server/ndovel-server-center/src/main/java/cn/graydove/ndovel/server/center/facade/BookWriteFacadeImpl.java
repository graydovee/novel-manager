package cn.graydove.ndovel.server.center.facade;

import cn.graydove.ndovel.server.api.facade.BookWriteFacade;
import cn.graydove.ndovel.server.api.model.request.*;
import cn.graydove.ndovel.server.center.service.BookService;
import cn.graydove.ndovel.server.center.service.ChapterService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author graydove
 */
@DubboService
@AllArgsConstructor
public class BookWriteFacadeImpl implements BookWriteFacade {

    private BookService bookService;

    private ChapterService chapterService;

    @Override
    public Long createBook(BookRequest bookRequest) {
        return bookService.createBook(bookRequest);
    }

    @Override
    public Long appendChapter(ChapterRequest chapterRequest) {
        return chapterService.appendChapter(chapterRequest);
    }

    @Override
    public Boolean updateBook(UpdateBookRequest updateBookRequest) {
        return bookService.updateBook(updateBookRequest);
    }

    @Override
    public Boolean deleteBook(BookDeleteRequest bookDeleteRequest) {
        return bookService.deleteBook(bookDeleteRequest);
    }

    @Override
    public Boolean updateChapter(UpdateChapterRequest updateChapterRequest) {
        return chapterService.updateChapter(updateChapterRequest);
    }


}
