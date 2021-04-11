package cn.graydove.ndovel.reader.service.impl;

import cn.graydove.common.response.Paging;
import cn.graydove.ndovel.reader.service.BookService;
import cn.graydove.ndovel.server.api.BookReadFacade;
import cn.graydove.ndovel.server.model.dto.BookPageDTO;
import cn.graydove.ndovel.server.model.dto.ChapterIdDTO;
import cn.graydove.ndovel.server.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.server.model.vo.BookVO;
import cn.graydove.ndovel.server.model.vo.ChapterVO;
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
