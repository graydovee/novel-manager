package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.repository.BookRepository;
import com.ndovel.ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAllIsExist();
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.selBookByName(name);
    }

    @Override
    public Optional<Book> findOneById(Integer id) {
        return bookRepository.findOneIsExist(id);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }
}
