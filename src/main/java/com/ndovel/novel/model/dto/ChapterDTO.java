package com.ndovel.novel.model.dto;


import com.ndovel.novel.model.dto.base.BaseDTO;
import com.ndovel.novel.model.entity.Chapter;
import lombok.*;

import java.util.Date;

@Data
@EqualsAndHashCode
public class ChapterDTO implements BaseDTO<ChapterDTO, Chapter> {
    private Integer id;

    private Integer bookId;

    private Integer nextChapterId;

    private Integer contentId;

    private String title;

    private Date createTime;
}
