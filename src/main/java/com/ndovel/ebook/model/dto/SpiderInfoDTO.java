package com.ndovel.ebook.model.dto;

import com.ndovel.ebook.model.dto.base.BaseDTO;
import com.ndovel.ebook.model.entity.SpiderInfo;
import lombok.Data;

@Data
public class SpiderInfoDTO implements BaseDTO<SpiderInfoDTO, SpiderInfo> {

    private Integer id;

    private BookDTO book;

    private String url;

    private MatchRexDTO matchRex;

    private ChapterDTO chapter;

    private Boolean deleted;

}
