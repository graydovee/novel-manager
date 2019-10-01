package com.ndovel.ebook.controller.admin;

import com.ndovel.ebook.model.dto.AuthorDTO;
import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.model.entity.Author;
import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.model.vo.Response;
import com.ndovel.ebook.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/admin")
@RestController
public class AdminSpiderController {

    @Autowired
    private SpiderService spiderService;

    @Autowired
    private MatchRexService matchRexService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ChapterService chapterService;

    @PostMapping("/book")
    public Response spider(String bookName, String authorName, String url, Integer matchRexId){

        if(bookName == null || authorName == null || url == null || matchRexId == null)
            return null;


        return Response.success(spiderService.spider(bookName, authorName, url ,matchRexId));
    }

    @GetMapping("/rex")
    public Response getAllRex(){
        return Response.success(matchRexService.getAllRex());
    }

    @PostMapping("/rex")
    public Response updMatchRex(MatchRexDTO matchRexDTO){
        return Response.success(matchRexService.save(matchRexDTO.writeToDomain()));
    }

    @DeleteMapping("/rex")
    public Response delMatchRex(Integer id){
        if(id != null){
            matchRexService.delById(id);
            return Response.success("success");
        }
        return Response.error("无效参数");
    }

    @DeleteMapping("/book")
    public Response delBook(Integer id){
        if(id != null){
            log.info("delete:" + id.toString());
            bookService.deleteBookById(id);
            chapterService.delChapterByBookId(id);
            return Response.success("success");
        }
        return Response.error("无效参数");
    }

}
