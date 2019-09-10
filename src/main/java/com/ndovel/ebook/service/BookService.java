package com.ndovel.ebook.service;

import com.ndovel.ebook.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBook();

    List<Book> findByName(String name);

    Optional<Book> findOneById(Integer id);

    void deleteBookById(Integer id);
}
