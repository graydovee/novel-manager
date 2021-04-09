package cn.graydove.server.controller;

import cn.graydove.common.query.PageQuery;
import cn.graydove.common.response.Paging;
import cn.graydove.server.model.dto.ChapterPageDTO;
import cn.graydove.server.model.vo.ChapterVO;
import cn.graydove.server.service.BookService;
import cn.graydove.server.service.ChapterService;
import lombok.AllArgsConstructor;
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

    @GetMapping("/chapter")
    public ChapterVO chapter(@Valid @NotNull Long chapterId) {
        return chapterService.findDetail(chapterId);
    }

    @GetMapping("/page_chapter")
    public Paging<ChapterVO> chapter(@Valid ChapterPageDTO chapterPageDTO) {
        return chapterService.pageByBookId(chapterPageDTO);
    }
}
