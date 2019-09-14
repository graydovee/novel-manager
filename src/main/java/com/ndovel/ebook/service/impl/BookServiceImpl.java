package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.repository.BookRepository;
import com.ndovel.ebook.repository.ChapterRepository;
import com.ndovel.ebook.service.BookService;
import com.ndovel.ebook.service.ChapterService;
import com.ndovel.ebook.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ChapterService chapterService;

    @Cacheable(cacheNames = {"book"})
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
    public Optional<BookDTO> findOneById(Integer id) {
        return bookRepository.findOneIsExist(id)
                .map(book -> new BookDTO().init(book));
    }

    @CacheEvict(cacheNames = {"book"}, allEntries = true)
    @Override
    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
        chapterService.delChapterByBookId(id);
    }

}
