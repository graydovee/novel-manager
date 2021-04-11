package cn.graydove.ndovel.reader.controller;

import cn.graydove.common.response.Paging;
import cn.graydove.ndovel.server.model.dto.BookPageDTO;
import cn.graydove.ndovel.server.model.dto.ChapterIdDTO;
import cn.graydove.ndovel.reader.service.BookService;
import cn.graydove.ndovel.server.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.server.model.vo.BookVO;
import cn.graydove.ndovel.server.model.vo.ChapterVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author graydove
 */
@RestController
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping("/chapter")
    public ChapterVO chapter(@Valid ChapterIdDTO chapterIdDTO) {
        return bookService.findChapter(chapterIdDTO);
    }

    @GetMapping("/page_chapter")
    public Paging<ChapterVO> chapter(@Valid ChapterPageDTO chapterPageDTO) {
        return bookService.pageChapter(chapterPageDTO);
    }

    @GetMapping("/page_book")
    public Paging<BookVO> book(@Valid BookPageDTO bookPageDTO) {
        return bookService.pageBook(bookPageDTO);
    }
}
