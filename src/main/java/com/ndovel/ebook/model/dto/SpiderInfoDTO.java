package com.ndovel.ebook.model.dto;

import com.ndovel.ebook.model.dto.base.BaseDTO;
import com.ndovel.ebook.model.entity.SpiderInfo;
import lombok.Data;

import java.util.Date;

@Data
public class SpiderInfoDTO implements BaseDTO<SpiderInfoDTO, SpiderInfo> {

    private Integer id;

    private BookDTO book;

    private String url;

    private MatchRexDTO matchRex;

    private ChapterDTO finalChapter;

    private Boolean deleted;

    private Date createTime;

}
