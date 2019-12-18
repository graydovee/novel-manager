package com.ndovel.ebook.model.dto;

import com.ndovel.ebook.model.dto.base.BaseDTO;
import com.ndovel.ebook.model.entity.Visit;
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
}
