package com.ndovel.novel.model.dto;

import com.ndovel.novel.model.dto.base.BaseDTO;
import com.ndovel.novel.model.entity.SpiderInfo;
import lombok.Data;

import java.util.Date;

@Data
public class SpiderInfoDTO implements BaseDTO<SpiderInfoDTO, SpiderInfo> {

    private Integer id;

    private BookDTO book;

    private String url;

    private MatchRexDTO matchRex;

    private ChapterDTO finalChapter;

    private Boolean finished;

    private Date createTime;

}
