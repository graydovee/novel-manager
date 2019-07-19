package cn.graydove.ebook.web.model.dto;

import cn.graydove.ebook.web.model.dto.base.BaseDTO;
import cn.graydove.ebook.web.model.entity.Book;
import lombok.*;

@Data
public class BookDTO implements BaseDTO<BookDTO, Book>{
    private Integer id;

    private String name;

    private String author;

    private Integer type;

    private Integer firstChapter;

}
