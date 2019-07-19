package cn.graydove.ebook.web.controller;

import cn.graydove.ebook.web.model.dto.BookDTO;
import cn.graydove.ebook.web.model.entity.Book;
import cn.graydove.ebook.web.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public List<BookDTO> getBook(String name){
        List<Book> books;
        if(name==null)
            books = bookService.getAllBook();
        else
            books = bookService.selBookByName(name);
        return new BookDTO().writeToDTOList(books);
    }

    @GetMapping("/exact")
    public BookDTO getExactBook(String name, String author){
        Book book = bookService.selBookByNameAndAuthor(name, author);
        return new BookDTO().init(book);
    }
}
