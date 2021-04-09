package cn.graydove.ndovel.spider.controller;

import cn.graydove.ndovel.spider.model.dto.BookDTO;
import cn.graydove.ndovel.spider.service.SpiderService;
import lombok.AllArgsConstructor;
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

    @PostMapping("/spiderBook")
    public void spiderBook(@RequestBody @Valid BookDTO bookDTO) {
        spiderService.spider(bookDTO);
    }

}
