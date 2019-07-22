package cn.graydove.ebook.web.model.dto;

import cn.graydove.ebook.web.model.dto.base.BaseDTO;
import cn.graydove.ebook.web.model.entity.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class AuthorDTO implements BaseDTO<AuthorDTO, Author> {
    Integer id;

    String name;
}
