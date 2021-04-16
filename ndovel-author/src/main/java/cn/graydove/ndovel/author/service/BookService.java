package cn.graydove.ndovel.author.service;

import cn.graydove.ndovel.author.model.dto.*;
import cn.graydove.ndovel.common.query.PageQuery;
import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;


/**
 * @author graydove
 */
public interface BookService {

    Paging<BookVO> listReviewFailBook(PageQuery pageQuery);

    Paging<ChapterVO> listReviewFailChapter(PageChapterDTO pageChapterDTO);

    Long createBook(BookDTO bookDTO);

    Long appendChapter(ChapterDTO chapterDTO);

    Boolean updateBook(UpdateBookDTO updateBookDTO);

    Boolean updateChapter(UpdateChapterDTO updateChapterDTO);

    Boolean submitBook(SubmitDTO submitDTO);

    Boolean submitChapter(SubmitDTO submitDTO);
}
