package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDTO> getAllBook();

    Page<BookDTO> findByName(String name, Integer index, Integer size);

    Optional<BookDTO> findExact(String bookName, String authorName);

    Page<BookDTO> find(Integer index, Integer size);

    Optional<BookDTO> findOneById(Integer id);

    void deleteBookById(Integer id);
}
