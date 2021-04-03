package cn.graydove.ndovel.spider.controller;

import cn.graydove.ndovel.spider.model.dto.BookDTO;
import cn.graydove.ndovel.spider.service.SpiderService;
import cn.graydove.server.model.vo.ChapterVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author graydove
 */
@RestController
@AllArgsConstructor
public class SpiderController {

    private SpiderService spiderService;

    @PostMapping("/spiderBook")
    public void spiderBook(@RequestBody @Valid BookDTO bookDTO) {
        spiderService.spider(bookDTO);
    }

    @PostMapping("/findChapter")
    public ChapterVO findChapter(@Valid @NotNull Long chapterId) {
        return spiderService.findChapter(chapterId);
    }
}
