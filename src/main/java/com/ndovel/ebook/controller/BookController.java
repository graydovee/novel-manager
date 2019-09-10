package com.ndovel.ebook.controller;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public List<BookDTO> getAllBook(){
        List<BookDTO> list = new ArrayList<>();
        List<Book> bookList = bookService.getAllBook();

        bookList.forEach(book -> list.add(new BookDTO().init(book)));

        return list;
    }

    @GetMapping("/find")
    public BookDTO exactBook(Integer id){
        BookDTO bookDTO = new BookDTO();

        bookService.findOneById(id)
                .ifPresent(bookDTO::init);

        return bookDTO;
    }

    @PostMapping("/find")
    public List<BookDTO> likeBook(String name){
        List<BookDTO> list = new ArrayList<>();
        List<Book> bookList = bookService.findByName(name);

        bookList.forEach(book -> list.add(new BookDTO().init(book)));

        return list;
    }
}
