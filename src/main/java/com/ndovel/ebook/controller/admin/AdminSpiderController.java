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
    public String spider(String bookName,String authorName, String url, String encode, Integer matchRexId){

        if(bookName == null || authorName == null || url == null || matchRexId == null)
            return ERROR;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(new AuthorDTO(authorName));
        bookDTO.setName(bookName);


        asyncSpiderService.downBook(bookDTO, url, encode ,matchRexId);
        return OK;
    }

    @GetMapping("/rex")
    public List<MatchRexDTO> getAllRex(){
        List<MatchRexDTO> list = new ArrayList<>();
        List<MatchRex> l = matchRexService.getAllRex();

        l.forEach(matchRex -> list.add(new MatchRexDTO().init(matchRex)));

        return list;
    }

    @PostMapping("/rex")
    public MatchRexDTO updMatchRex(MatchRexDTO matchRexDTO){
        log.info(matchRexDTO.toString());
        MatchRex matchRex = matchRexService.save(matchRexDTO.writeToDomain());

        return new MatchRexDTO().init(matchRex);
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
