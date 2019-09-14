package com.ndovel.ebook.controller;

import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;
import com.ndovel.ebook.model.entity.Chapter;
import com.ndovel.ebook.service.ChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/chapter")
    public List<ChapterDTO> findAllChapter(Integer bookId){
        return chapterService.findAllChapterByBookId(bookId);
    }

    @GetMapping("/content")
    public ContentDTO findContent(Integer id){
        return chapterService.findContentById(id)
                .orElse(null);
    }
}
