package com.ndovel.novel.model.dto;

import com.ndovel.novel.model.dto.base.BaseDTO;
import com.ndovel.novel.model.entity.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class AuthorDTO implements BaseDTO<AuthorDTO, Author> {
    public AuthorDTO(String name) {
        this.name = name;
    }

    private Integer id;
    private String name;
}
