package com.ndovel.ebook.model.dto;

import com.ndovel.ebook.model.dto.base.BaseDTO;
import com.ndovel.ebook.model.entity.Author;
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
