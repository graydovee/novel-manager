package com.ndovel.ebook.model.dto;

import com.ndovel.ebook.model.dto.base.BaseDTO;
import com.ndovel.ebook.model.entity.MatchRex;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MatchRexDTO implements BaseDTO<MatchRexDTO, MatchRex> {

    private Integer id;

    private String name;

    private String info;

    private String ContentRex;

    private String titleRex;

    private String nextPageRex;
}
