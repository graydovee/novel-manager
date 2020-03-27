package com.ndovel.novel.controller;

import com.ndovel.novel.model.dto.VisitDTO;
import com.ndovel.novel.model.vo.Response;
import com.ndovel.novel.service.VisitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class VisitController {

    private VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/visit")
    public Response visit(Integer bookId) {
        if (bookId != null && bookId > 0) {
            return Response.success(visitService.getData(bookId, null, null).stream()
                    .mapToLong(VisitDTO::getVisit).sum());
        } else {
            return Response.success(visitService.getSum());
        }
    }

    @GetMapping("/admin/visit")
    public Response detail(Integer bookId, Date begin, Date end) {
        if (bookId != null && bookId > 0) {
            return Response.success(visitService.getData(bookId, begin, end));
        }
        return Response.success(visitService.getData(begin, end));
    }


}
