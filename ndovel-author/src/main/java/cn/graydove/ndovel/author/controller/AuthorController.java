package cn.graydove.ndovel.author.controller;

import cn.graydove.ndovel.author.model.dto.*;
import cn.graydove.ndovel.author.service.BookService;
import cn.graydove.ndovel.common.query.PageQuery;
import cn.graydove.ndovel.common.response.Paging;
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
public class AuthorController {

    private BookService bookService;

    @PostMapping("/create_book")
    public Long createBook(@RequestBody @Valid BookDTO bookDTO){
        return bookService.createBook(bookDTO);
    }

    @PostMapping("/create_chapter")
    public Long createChapter(@RequestBody @Valid ChapterDTO chapterDTO){
        return bookService.appendChapter(chapterDTO);
    }

    @PostMapping("/update_book")
    public Boolean updateBook(@RequestBody @Valid UpdateBookDTO updateBookDTO){
        return bookService.updateBook(updateBookDTO);
    }

    @PostMapping("/update_chapter")
    public Boolean updateChapter(@RequestBody @Valid UpdateChapterDTO updateChapterDTO){
        return bookService.updateChapter(updateChapterDTO);
    }

    @PostMapping("/submit_chapter")
    public Boolean submitChapter(@RequestBody @Valid SubmitDTO submitDTO){
        return bookService.submitChapter(submitDTO);
    }

    @PostMapping("/submit_book")
    public Boolean submitBook(@RequestBody @Valid SubmitDTO submitDTO){
        return bookService.submitBook(submitDTO);
    }


    @GetMapping("/list_fail_book")
    public Paging<BookVO> listReviewFailBook(PageQuery pageQuery) {
        return bookService.listReviewFailBook(pageQuery);
    }


    @GetMapping("/list_fail_chapter")
    public Paging<ChapterVO> listReviewFailChapter(PageChapterDTO pageChapterDTO) {
        return bookService.listReviewFailChapter(pageChapterDTO);
    }
}
