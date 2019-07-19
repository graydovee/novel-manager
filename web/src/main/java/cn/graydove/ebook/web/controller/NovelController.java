package cn.graydove.ebook.web.controller;

import cn.graydove.ebook.web.model.dto.StatusDTO;
import cn.graydove.ebook.web.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovelController {

    @Autowired
    private SpiderService spiderService;

    @PostMapping("/down")
    public StatusDTO requestB (String bookName,String author, String bookNumber, String firstPage, String finalPage){
        spiderService.downBook(bookName, author, bookNumber, firstPage, finalPage);
        return new StatusDTO("正在下载");
    }
}
