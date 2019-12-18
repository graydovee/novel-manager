package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.dto.VisitDTO;
import com.ndovel.ebook.model.entity.Visit;
import com.ndovel.ebook.repository.BookRepository;
import com.ndovel.ebook.repository.VisitRepository;
import com.ndovel.ebook.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        log.info("开始更新阅读量：");
        List<Integer> allBookId = bookRepository.findAllBookId();
        for (Integer bookId : allBookId) {
            Long visits = bookRepository.countVisitByBookId(bookId);

            if (visits > 0) {
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
        if (bookId == null) {
            return getData(begin, end);
        } else if (begin == null) {
            return visitRepository.selAllByBookId(bookId).stream()
                    .peek(visitDTO -> visitDTO.setBookId(bookId))
                    .collect(Collectors.toList());

        }
        if (end == null) {
            end = new Date();
        }
        return visitRepository.selAllByBookIdAndTime(bookId, begin, end).stream()
                .peek(visitDTO -> visitDTO.setBookId(bookId))
                .collect(Collectors.toList());
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
}
