package com.ndovel.novel.service.impl;

import com.ndovel.novel.model.dto.BookDTO;
import com.ndovel.novel.model.dto.VisitDTO;
import com.ndovel.novel.model.entity.Visit;
import com.ndovel.novel.repository.BookRepository;
import com.ndovel.novel.repository.VisitRepository;
import com.ndovel.novel.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class VisitServiceImpl implements VisitService {

    private VisitRepository visitRepository;
    private BookRepository bookRepository;

    public VisitServiceImpl(VisitRepository visitRepository, BookRepository bookRepository) {
        this.visitRepository = visitRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Long getSum() {
        return visitRepository.sum();
    }

    @Transactional
    @Override
    public void updateVisit() {
        List<Integer> allBookId = bookRepository.findAllBookId();
        for (Integer bookId : allBookId) {
            Long visits = bookRepository.countVisitByBookId(bookId);

            if (visits != null && visits > 0) {
                Visit visit = new Visit();
                visit.setBookId(bookId);
                visit.setVisit(visits);
                visit.setDate(new Date());
                visitRepository.save(visit);
                bookRepository.clearVisitByBookId(bookId);
                log.info("ID为{}的小说新增阅读量：{}", bookId, visits);
            } else {
                log.info("ID为{}的小说阅读量无需更新", bookId);
            }
        }
        log.info("更新阅读量完毕！");
    }

    @Override
    public List<VisitDTO> getData(Integer bookId, Date begin, Date end) {
        List<VisitDTO> visitDTOS;
        if (bookId == null) {
            return getData(begin, end);
        } else if (begin == null) {
            visitDTOS = visitRepository.selAllByBookId(bookId);
            visitDTOS.forEach(visitDTO -> visitDTO.setBookId(bookId));
            return visitDTOS;
        }
        if (end == null) {
            end = new Date();
        }
        visitDTOS = visitRepository.selAllByBookIdAndTime(bookId, begin, end);
        visitDTOS.forEach(visitDTO -> visitDTO.setBookId(bookId));
        return visitDTOS;
    }

    @Override
    public List<VisitDTO> getData(Date begin, Date end) {
        if (begin == null) {
            return visitRepository.selAll();
        }
        if (end == null) {
            end = new Date();
        }
        return visitRepository.selAllByTime(begin, end);
    }

    @Override
    @Transactional
    public List<VisitDTO> getBookTopN() {
        return visitRepository.selBooksBVisit();
    }
}
