package com.ndovel.ebook.controller;

import com.ndovel.ebook.model.vo.Response;
import com.ndovel.ebook.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/chapter")
    public Response findAllChapter(Integer bookId){
        return Response.success(chapterService.findAllChapterByBookId(bookId));
    }

    @GetMapping("/content")
    public Response findContent(Integer id){
        return Response.success(chapterService.findContentById(id)
                .orElse(null));
    }
}
