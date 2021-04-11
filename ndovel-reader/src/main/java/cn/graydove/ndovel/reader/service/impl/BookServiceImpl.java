package cn.graydove.ndovel.reader.service.impl;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.reader.service.BookService;
import cn.graydove.ndovel.server.api.facade.BookReadFacade;
import cn.graydove.ndovel.server.api.model.dto.BookPageDTO;
import cn.graydove.ndovel.server.api.model.dto.ChapterIdDTO;
import cn.graydove.ndovel.server.api.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author graydove
 */
@Service
public class BookServiceImpl implements BookService {

    @DubboReference
    private BookReadFacade bookReadFacade;

    @Override
    public ChapterVO findChapter(ChapterIdDTO chapterIdDTO) {
        return bookReadFacade.findChapter(chapterIdDTO);
    }

    @Override
    public Paging<ChapterVO> pageChapter(ChapterPageDTO chapterPageDTO) {
        return bookReadFacade.pageChapter(chapterPageDTO);
    }

    @Override
    public Paging<BookVO> pageBook(BookPageDTO bookPageDTO) {
        return bookReadFacade.pageBook(bookPageDTO);
    }
}
