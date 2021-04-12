package cn.graydove.ndovel.server.center.service.impl;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.api.model.request.BookPageRequest;
import cn.graydove.ndovel.server.api.model.request.UpdateBookRequest;
import cn.graydove.ndovel.server.center.model.entity.Author;
import cn.graydove.ndovel.server.center.model.entity.Book;
import cn.graydove.ndovel.server.center.model.entity.Category;
import cn.graydove.ndovel.server.api.model.request.BookRequest;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.center.repository.AuthorRepository;
import cn.graydove.ndovel.server.center.repository.BookRepository;
import cn.graydove.ndovel.server.center.repository.CategoryRepository;
import cn.graydove.ndovel.server.center.service.BookService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author graydove
 */
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private CategoryRepository categoryRepository;

    private AuthorRepository authorRepository;

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public Long createBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setIntroduce(bookRequest.getIntroduce());
        Author author = authorRepository.findByName(bookRequest.getAuthor())
                .orElseGet(() -> authorRepository.save(new Author(bookRequest.getAuthor())));
        book.setAuthor(author);
        Set<Category> categorySet = Optional.ofNullable(bookRequest.getCategory())
                .map(categories -> categories.stream()
                    .map(categoryName -> categoryRepository.findByName(categoryName).orElseGet(() -> categoryRepository.save(new Category(categoryName))))
                    .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
        book.setCategory(categorySet);
        book.setCover(bookRequest.getCover());
        book.setStatus(bookRequest.getStatus());
        book = bookRepository.save(book);
        return book.getId();
    }

    @Override
    public Paging<BookVO> pageBook(BookPageRequest bookPageRequest) {
        Page<Book> bookPage;
        if (CollectionUtil.isEmpty(bookPageRequest.getStatuses())) {
            bookPage = bookRepository.findAll(bookPageRequest.toPageable());
        } else {
            bookPage = bookRepository.findAllByStatusIn(bookPageRequest.getStatuses(), bookPageRequest.toPageable());
        }
        return Paging.ofWithMap(bookPage, book->BeanUtil.toBean(book, BookVO.class));
    }

    @Override
    public Boolean updateBook(UpdateBookRequest updateBookRequest) {
        return bookRepository.findById(updateBookRequest.getId()).map(book -> {
            BeanUtil.copyProperties(updateBookRequest, book, CopyOptions.create(null, true));
            bookRepository.save(book);
            return true;
        }).orElse(false);
    }
}
