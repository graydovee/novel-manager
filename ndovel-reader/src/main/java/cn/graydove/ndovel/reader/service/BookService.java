package cn.graydove.ndovel.reader.service;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.model.dto.BookPageDTO;
import cn.graydove.ndovel.server.model.dto.ChapterIdDTO;
import cn.graydove.ndovel.server.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.server.model.vo.BookVO;
import cn.graydove.ndovel.server.model.vo.ChapterVO;

/**
 * @author graydove
 */
public interface BookService {

    ChapterVO findChapter(ChapterIdDTO chapterIdDTO);

    Paging<ChapterVO> pageChapter(ChapterPageDTO chapterPageDTO);

    Paging<BookVO> pageBook(BookPageDTO bookPageDTO);
}
