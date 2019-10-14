package com.ndovel.ebook.service;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.dto.ChapterDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDTO> getAllBook();

    List<BookDTO> findByName(String name);

    Page<BookDTO> find(Integer index, Integer size);

    Optional<BookDTO> findOneById(Integer id);

    void deleteBookById(Integer id);

    Long visit(Integer bookId);

    Long sumVisit();
}
