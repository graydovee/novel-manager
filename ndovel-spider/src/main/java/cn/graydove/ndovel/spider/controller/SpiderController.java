package cn.graydove.ndovel.spider.controller;

import cn.graydove.ndovel.spider.model.dto.BookDTO;
import cn.graydove.ndovel.spider.model.dto.BookDeleteDTO;
import cn.graydove.ndovel.spider.model.dto.ChapterDTO;
import cn.graydove.ndovel.spider.service.SpiderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author graydove
 */
@RestController
@AllArgsConstructor
public class SpiderController {

    private SpiderService spiderService;

    @PostMapping("/book")
    public Long createBook(@RequestBody @Valid BookDTO bookDTO) {
        return spiderService.createBook(bookDTO);
    }

    @PostMapping("/chapter")
    public void createChapter(@RequestBody @Valid ChapterDTO chapterDTO) {
        spiderService.createChapter(chapterDTO);
    }

    @DeleteMapping("/book")
    public void deleteBook(@Valid BookDeleteDTO bookDeleteDTO) {
        spiderService.deleteBook(bookDeleteDTO);
    }
}
