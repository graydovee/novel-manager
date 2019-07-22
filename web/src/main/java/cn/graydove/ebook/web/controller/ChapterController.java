package cn.graydove.ebook.web.controller;

import cn.graydove.ebook.web.model.dto.BookDTO;
import cn.graydove.ebook.web.model.dto.ChapterDTO;
import cn.graydove.ebook.web.model.entity.Chapter;
import cn.graydove.ebook.web.model.vo.ChapterVO;
import cn.graydove.ebook.web.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @PostMapping("/first_page")
    public ChapterDTO firstPage(BookDTO book){
        Chapter c = chapterService.getFirstChapter(book.writeToDomain());
        return new ChapterDTO().init(c);
    }

    @PostMapping("/next_page")
    public ChapterDTO nextPage(ChapterDTO chapterDTO){
        Chapter c = chapterService.getNextChapter(chapterDTO.writeToDomain());
        return new ChapterDTO().init(c);
    }

    @GetMapping("/all_chapter")
    public List<ChapterVO>  getAllChapter(Integer bookId){
        return chapterService.findAllByBookId(bookId);
    }
}
