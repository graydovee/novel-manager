package com.ndovel.novel.service;

import com.ndovel.novel.model.dto.VisitDTO;

import java.util.Date;
import java.util.List;

public interface VisitService {

    Long getSum();

    void updateVisit();

    List<VisitDTO> getData(Integer bookId, Date begin, Date end);

    List<VisitDTO> getData(Date begin, Date end);

    List<VisitDTO> getBookTopN();

}
