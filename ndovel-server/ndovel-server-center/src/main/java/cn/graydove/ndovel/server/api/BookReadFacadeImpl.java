package cn.graydove.ndovel.server.api;

import cn.graydove.common.response.Paging;
import cn.graydove.ndovel.server.model.dto.BookPageDTO;
import cn.graydove.ndovel.server.model.dto.ChapterIdDTO;
import cn.graydove.ndovel.server.model.vo.BookVO;
import cn.graydove.ndovel.server.service.BookService;
import cn.graydove.ndovel.server.service.ChapterService;
import cn.graydove.ndovel.server.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.server.model.vo.ChapterVO;
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
    public ChapterVO findChapter(ChapterIdDTO chapterIdDTO) {
        return chapterService.findDetail(chapterIdDTO.getChapterId());
    }

    @Override
    public Paging<ChapterVO> pageChapter(ChapterPageDTO chapterPageDTO) {
        return chapterService.pageByBookId(chapterPageDTO);
    }

    @Override
    public Paging<BookVO> pageBook(BookPageDTO bookPageDTO) {
        return bookService.pageBook(bookPageDTO);
    }
}
