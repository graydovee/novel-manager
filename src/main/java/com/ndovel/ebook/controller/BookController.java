package com.ndovel.ebook.controller;

import com.ndovel.ebook.model.vo.Response;
import com.ndovel.ebook.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public Response getAllBook(Integer index, Integer size){
        if(index==null || size==null || size<=0)
            return Response.success(bookService.getAllBook());
        else
            return Response.success(bookService.find(index, size));
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

    @GetMapping("/visit")
    public Response visit(Integer bookId){
        if(bookId!=null && bookId>0){
            return Response.success(bookService.visit(bookId));
        }
        return Response.success(bookService.sumVisit());
    }
}
