package com.ndovel.ebook.model.dto;

import com.ndovel.ebook.model.dto.base.BaseDTO;
import com.ndovel.ebook.model.entity.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
public class BookDTO implements BaseDTO<BookDTO, Book> {
    private String name;

    private AuthorDTO author;

    private Set<TypeDTO> type = new HashSet<>();

    private Integer firstChapter;
}
