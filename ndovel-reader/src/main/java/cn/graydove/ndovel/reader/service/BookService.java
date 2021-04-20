package cn.graydove.ndovel.reader.service;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.reader.model.dto.BookPageDTO;
import cn.graydove.ndovel.reader.model.dto.ChapterIdDTO;
import cn.graydove.ndovel.reader.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.reader.model.dto.NovelSearchDTO;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import cn.graydove.ndovel.server.api.model.vo.NovelVO;

/**
 * @author graydove
 */
public interface BookService {

    ChapterVO findChapter(ChapterIdDTO chapterIdDTO);

    Paging<ChapterVO> pageChapter(ChapterPageDTO chapterPageDTO);

    Paging<BookVO> pageBook(BookPageDTO bookPageDTO);

    Paging<NovelVO> searchNovel(NovelSearchDTO novelSearchDTO);
}
