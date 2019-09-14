package com.ndovel.ebook.controller.admin;

import com.ndovel.ebook.model.dto.AuthorDTO;
import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.model.entity.MatchRex;
import com.ndovel.ebook.service.AsyncSpiderService;
import com.ndovel.ebook.service.BookService;
import com.ndovel.ebook.service.ChapterService;
import com.ndovel.ebook.service.MatchRexService;
import com.ndovel.ebook.spider.bean.TaskCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/admin")
@RestController
public class AdminSpiderController {
    private static String OK = "OK";
    private static String ERROR = "ERROR";

    @Autowired
    private AsyncSpiderService asyncSpiderService;

    @Autowired
    private MatchRexService matchRexService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ChapterService chapterService;



    @PostMapping("/book")
    public BookDTO spider(String bookName,String authorName, String url, String encode, Integer matchRexId){

        if(bookName == null || authorName == null || url == null || matchRexId == null)
            return null;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(new AuthorDTO(authorName));
        bookDTO.setName(bookName);


        return asyncSpiderService.spider(bookDTO, url, encode ,matchRexId);
    }

    @GetMapping("/rex")
    public List<MatchRexDTO> getAllRex(){
        return matchRexService.getAllRex();
    }

    @PostMapping("/rex")
    public MatchRexDTO updMatchRex(MatchRexDTO matchRexDTO){
        return matchRexService.save(matchRexDTO.writeToDomain());
    }

    @DeleteMapping("/rex")
    public String delMatchRex(Integer id){
        if(id != null){
            matchRexService.delById(id);
            return OK;
        }
        return ERROR;
    }

    @DeleteMapping("/book")
    public String delBook(Integer id){
        if(id != null){
            log.info("delete:" + id.toString());
            bookService.deleteBookById(id);
            chapterService.delChapterByBookId(id);
            return OK;
        }
        return ERROR;
    }
}
