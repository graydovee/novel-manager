package cn.graydove.ebook.web.service.impl;

import cn.graydove.ebook.web.model.entity.Book;
import cn.graydove.ebook.web.repository.BookRepository;
import cn.graydove.ebook.web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> selBookByName(String name) {
        return bookRepository.selBookByName(name);
    }

    @Override
    public Book selBookByNameAndAuthor(String name, String author){
        return bookRepository.selBookByNameAndAuthor(name, author);
    }

    @Override
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }
}
