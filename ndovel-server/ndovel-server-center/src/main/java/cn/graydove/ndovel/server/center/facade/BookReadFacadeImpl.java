package cn.graydove.ndovel.server.center.facade;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.facade.BookReadFacade;
import cn.graydove.ndovel.server.api.model.dto.BookPageDTO;
import cn.graydove.ndovel.server.api.model.dto.ChapterIdDTO;
import cn.graydove.ndovel.server.api.model.dto.ChapterPageDTO;
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
