package com.ndovel.ebook.controller;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.vo.Response;
import com.ndovel.ebook.service.BookService;
import com.ndovel.ebook.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private SpiderService spiderService;

    @GetMapping("/book")
    public Response getAllBook(){
        return Response.success(bookService.getAllBook());
    }

    @GetMapping("/spider")
    public Response spiderOne(String url, String encode, Integer matchRexId){
        return Response.success(spiderService.spiderOne(url, encode, matchRexId));
    }

    @GetMapping("/find")
    public Response exactBook(Integer id){
        return Response.success(bookService.findOneById(id)
                .orElse(null));
    }

    @PostMapping("/find")
    public Response likeBook(String name){
        return Response.success(bookService.findByName(name));
    }
}
