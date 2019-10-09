package com.ndovel.ebook.controller;

import com.ndovel.ebook.model.vo.Response;
import com.ndovel.ebook.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/chapter")
    public Response findAllChapter(Integer bookId, Integer pageIndex, Integer pageSize){
        if(bookId==null || bookId==0){
            return Response.error("查无此书");
        }

        if(pageIndex!=null && pageSize !=null){
            return Response.success(chapterService.find(bookId,pageIndex,pageSize));
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
