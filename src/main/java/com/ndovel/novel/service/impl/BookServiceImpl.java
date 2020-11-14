package com.ndovel.novel.service.impl;

import com.ndovel.novel.model.dto.BookDTO;
import com.ndovel.novel.model.entity.Book;
import com.ndovel.novel.repository.BookRepository;
import com.ndovel.novel.repository.SpiderInfoRepository;
import com.ndovel.novel.repository.VisitRepository;
import com.ndovel.novel.service.BookService;
import com.ndovel.novel.service.ChapterService;
import com.ndovel.novel.utils.DTOUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ChapterService chapterService;
    private SpiderInfoRepository spiderInfoRepository;

    public BookServiceImpl(BookRepository bookRepository, ChapterService chapterService, SpiderInfoRepository spiderInfoRepository) {
        this.bookRepository = bookRepository;
        this.chapterService = chapterService;
        this.spiderInfoRepository = spiderInfoRepository;
    }

    @Override
    public List<BookDTO> getAllBook() {
        List<Book> bookList = bookRepository.findAllIsExist();
        return DTOUtils.listToDTOs(bookList, BookDTO.class);
    }

    @Override
    public Optional<BookDTO> findExact(String bookName, String authorName) {
        return bookRepository.selBookByNameAndAuthor(bookName, authorName)
                .map(book -> new BookDTO().init(book));
    }

    @Override
    public Page<BookDTO> findByName(String name, Integer index, Integer size) {

        Specification<Book> specification = (root, query, cb) -> {
            Predicate nameLike = cb.like(root.get("name"), "%" + name + "%");
            Predicate authorLike = cb.like(root.get("author").get("name"), "%" + name + "%");
            query.orderBy(cb.desc(root.get("id")));
            return cb.or(nameLike, authorLike);
        };

        return bookRepository.findIsExist(specification, PageRequest.of(index, size))
                .map(book -> new BookDTO().init(book));
    }

    @Override
    public Page<BookDTO> find(Integer index, Integer size) {
        Pageable pageable = PageRequest.of(index, size);
        return bookRepository.findIsExist(pageable, true)
                .map(book -> new BookDTO().init(book));
    }

    @Override
    public Optional<BookDTO> findOneById(Integer id) {
        return bookRepository.findOneIsExist(id)
                .map(book -> new BookDTO().init(book));
    }

    @Transactional
    @Override
    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
        chapterService.delChapterByBookId(id);
        spiderInfoRepository.deleteByBookId(id);
    }

}
