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
        List<ChapterDTO> list = new ArrayList<>();
        List<Chapter> l = chapterService.findAllChapterByBookId(bookId);

        l.forEach(chapter -> list.add(new ChapterDTO().init(chapter)));
        return list;
    }

    @GetMapping("/content")
    public ContentDTO findContent(Integer id){
        ContentDTO contentDTO = new ContentDTO();

        chapterService.findContentById(id).ifPresent(contentDTO::init);

        return contentDTO;
    }
}
