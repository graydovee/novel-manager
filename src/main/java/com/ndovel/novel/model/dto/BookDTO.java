package com.ndovel.novel.model.dto;

import com.ndovel.novel.model.dto.base.BaseDTO;
import com.ndovel.novel.model.entity.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
public class BookDTO implements BaseDTO<BookDTO, Book> {
    private Integer id;

    private String name;

    private AuthorDTO author;

    private Set<TypeDTO> type = new HashSet<>();

    private Integer firstChapter;

    private Date createTime;

    private String introduce;

}
