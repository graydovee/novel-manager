package com.ndovel.novel.model.dto;

import com.ndovel.novel.model.dto.base.BaseDTO;
import com.ndovel.novel.model.entity.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class TypeDTO implements BaseDTO<TypeDTO, Type> {

    private Integer id;

    private String name;
}
