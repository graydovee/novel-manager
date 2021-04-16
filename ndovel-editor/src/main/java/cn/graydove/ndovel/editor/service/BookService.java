package cn.graydove.ndovel.editor.service;

import cn.graydove.ndovel.common.query.PageQuery;
import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.editor.model.dto.AuditDTO;
import cn.graydove.ndovel.editor.model.dto.ChapterDTO;
import cn.graydove.ndovel.editor.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;

/**
 * @author graydove
 */
public interface BookService {

    Paging<BookVO> pageReviewBook(PageQuery pageQuery);

    Paging<ChapterVO> pageReviewChapter(ChapterPageDTO chapterPageDTO);

    Boolean auditBook(AuditDTO auditDTO);

    Boolean auditChapter(AuditDTO auditDTO);

    ChapterVO findChapterById(ChapterDTO chapterDTO);
}
