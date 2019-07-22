package cn.graydove.ebook.web.model.dto;

import cn.graydove.ebook.web.model.dto.base.BaseDTO;
import cn.graydove.ebook.web.model.entity.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class TypeDTO implements BaseDTO<TypeDTO, Type> {
    private Integer id;

    private String name;
}
