package cn.graydove.ebook.web.service.impl;

import cn.graydove.ebook.web.model.entity.Book;
import cn.graydove.ebook.web.model.entity.Chapter;
import cn.graydove.ebook.web.model.vo.ChapterVO;
import cn.graydove.ebook.web.repository.ChapterRepository;
import cn.graydove.ebook.web.service.ChapterService;
import cn.graydove.ebook.web.utils.OptionalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public Chapter getFirstChapter(Book book) {
        Optional<Chapter> optionalChapter = chapterRepository.findById(book.getFirstChapter());
        return OptionalUtils.getValue(optionalChapter);
    }

    @Override
    public Chapter getNextChapter(Chapter chapter) {
        return chapterRepository.selChapterByThisPage(chapter.getNextPage(), chapter.getBookId());
    }

    @Override
    public List<ChapterVO> findAllByBookId(Integer bookId){
        return chapterRepository.findAllChapterByBookId(bookId);
    }

}
