package cn.graydove.ebook.web.model.dto;

import cn.graydove.ebook.web.model.dto.base.BaseDTO;
import cn.graydove.ebook.web.model.entity.Book;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
public class BookDTO implements BaseDTO<BookDTO, Book>{
    private Integer id;

    private String name;

    private AuthorDTO author;

    private Set<TypeDTO> type = new HashSet<TypeDTO>();

    private Integer firstChapter;

}
