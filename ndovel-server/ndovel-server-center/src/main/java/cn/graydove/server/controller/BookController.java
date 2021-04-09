package cn.graydove.server.controller;

import cn.graydove.server.model.vo.ChapterVO;
import cn.graydove.server.service.BookService;
import cn.graydove.server.service.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author graydove
 */
@RestController
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    private ChapterService chapterService;

    @GetMapping("/findChapter")
    public ChapterVO findChapter(@Valid @NotNull Long chapterId) {
        return chapterService.find(chapterId);
    }
}
