package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDTO> getAllBook();

    List<BookDTO> findByName(String name);

    Optional<BookDTO> findOneById(Integer id);

    void deleteBookById(Integer id);

    Long visit(Integer bookId);

    Long sumVisit();
}
