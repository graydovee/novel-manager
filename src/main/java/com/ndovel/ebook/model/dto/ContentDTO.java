package com.ndovel.ebook.model.dto;

import com.ndovel.ebook.model.dto.base.BaseDTO;
import com.ndovel.ebook.model.entity.Content;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ContentDTO implements BaseDTO<ContentDTO, Content> {

    private Integer id;

    private String info;

}
