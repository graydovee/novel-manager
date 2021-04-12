package cn.graydove.ndovel.reader.controller;

import cn.graydove.ndovel.client.UserContext;
import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.reader.model.dto.BookPageDTO;
import cn.graydove.ndovel.reader.model.dto.ChapterIdDTO;
import cn.graydove.ndovel.reader.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.reader.service.BookService;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author graydove
 */
@Slf4j
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
