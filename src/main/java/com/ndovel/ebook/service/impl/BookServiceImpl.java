package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.model.entity.Visit;
import com.ndovel.ebook.repository.BookRepository;
import com.ndovel.ebook.repository.SpiderInfoRepository;
import com.ndovel.ebook.repository.VisitRepository;
import com.ndovel.ebook.service.BookService;
import com.ndovel.ebook.service.ChapterService;
import com.ndovel.ebook.utils.DTOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ChapterService chapterService;
    private VisitRepository visitRepository;
    private SpiderInfoRepository spiderInfoRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           ChapterService chapterService,
                           VisitRepository visitRepository,
                           SpiderInfoRepository spiderInfoRepository) {
        this.bookRepository = bookRepository;
        this.chapterService = chapterService;
        this.visitRepository = visitRepository;
        this.spiderInfoRepository = spiderInfoRepository;
    }

    @Override
    public List<BookDTO> getAllBook() {
        List<Book> bookList = bookRepository.findAllIsExist();
        return DTOUtils.listToDTOs(bookList, BookDTO.class);
    }

    @Override
    public List<BookDTO> findByName(String name) {
        List<Book> books = bookRepository.selBookByName(name);

        return DTOUtils.listToDTOs(books, BookDTO.class);
    }

    @Override
    public Page<BookDTO> find(Integer index, Integer size) {
        Pageable pageable = PageRequest.of(index, size);
        return bookRepository.findIsExist(pageable)
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

    @Override
    public Long visit(Integer bookId){
        return visitRepository.findByBookId(bookId)
                .map(Visit::getVisit)
                .orElse(0L);
    }

    @Override
    public Long sumVisit() {
        return visitRepository.sum();
    }
}
