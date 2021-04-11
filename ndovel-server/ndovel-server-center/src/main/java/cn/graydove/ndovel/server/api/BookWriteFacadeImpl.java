package cn.graydove.ndovel.server.api;

import cn.graydove.ndovel.server.service.BookService;
import cn.graydove.ndovel.server.service.ChapterService;
import cn.graydove.ndovel.server.model.request.BookRequest;
import cn.graydove.ndovel.server.model.request.ChapterRequest;
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

}
