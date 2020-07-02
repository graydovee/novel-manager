package com.ndovel.novel.model.dto;

import com.ndovel.novel.model.dto.base.BaseDTO;
import com.ndovel.novel.model.entity.Visit;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class VisitDTO implements BaseDTO<VisitDTO, Visit> {
    private Integer bookId;

    private Long visit;

    private Date date;

    public VisitDTO(Long visit, Date date) {
        this.visit = visit;
        this.date = date;
    }

    public VisitDTO(Integer bookId, Long visit) {
        this.bookId = bookId;
        this.visit = visit;
    }
}
