package cn.graydove.server.api;

import cn.graydove.server.model.request.BookRequest;
import cn.graydove.server.model.request.ChapterRequest;
import cn.graydove.server.model.vo.ChapterVO;
import cn.graydove.server.service.BookService;
import cn.graydove.server.service.CategoryService;
import cn.graydove.server.service.ChapterService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

@DubboService
@AllArgsConstructor
public class BookFacadeImpl implements BookFacade {

    private BookService bookService;

    private ChapterService chapterService;

    @Override
    @Transactional(rollbackFor = {Throwable.class})
    public Long createBook(BookRequest bookRequest) {
        return bookService.createBook(bookRequest);
    }

    public Long appendChapter(ChapterRequest chapterRequest) {
        return chapterService.appendChapter(chapterRequest);
    }

    @Override
    public ChapterVO findChapter(Long chapterId) {
        return chapterService.find(chapterId);
    }

}
