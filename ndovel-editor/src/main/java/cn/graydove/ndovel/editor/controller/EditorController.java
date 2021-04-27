package cn.graydove.ndovel.editor.controller;

import cn.graydove.ndovel.common.query.PageQuery;
import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.editor.model.dto.AuditDTO;
import cn.graydove.ndovel.editor.model.dto.ChapterDTO;
import cn.graydove.ndovel.editor.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.editor.service.BookService;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author graydove
 */
@RestController
@AllArgsConstructor
public class EditorController {

    private BookService bookService;

    @GetMapping("/page_book")
    public Paging<BookVO> pageReviewBook(PageQuery pageQuery) {
        return bookService.pageReviewBook(pageQuery);
    }

    @GetMapping("/page_chapter")
    public Paging<ChapterVO> pageReviewChapter(ChapterPageDTO chapterPageDTO) {
        return bookService.pageReviewChapter(chapterPageDTO);
    }

    @GetMapping("/chapter")
    public ChapterVO findChapterById(@Valid ChapterDTO chapterDTO) {
        return bookService.findChapterById(chapterDTO);
    }

    @PostMapping("/audit_book")
    public Boolean auditBook(@RequestBody @Valid AuditDTO auditDTO) {
        return bookService.auditBook(auditDTO);
    }

    @PostMapping("/audit_chapter")
    public Boolean auditChapter(@RequestBody @Valid AuditDTO auditDTO) {
        return bookService.auditChapter(auditDTO);
    }
}
