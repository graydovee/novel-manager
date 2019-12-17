package com.ndovel.ebook.controller;

import com.ndovel.ebook.model.vo.Response;
import com.ndovel.ebook.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChapterController {

    private ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/chapter")
    public Response findAllChapter(Integer bookId, Integer index, Integer size){
        if(bookId==null || bookId==0){
            return Response.error("查无此书");
        }

        if(index!=null && size !=null){
            return Response.success(chapterService.find(bookId, index, size));
        }

        return Response.success(chapterService.findAllChapterByBookId(bookId));
    }

    @PostMapping("/chapter")
    public Response chapter(Integer chapterId){
        return Response.success(chapterService.findById(chapterId));
    }


    @GetMapping("/content")
    public Response findContent(Integer id){
        return Response.success(chapterService.findContentById(id)
                .orElse(null));
    }
}
