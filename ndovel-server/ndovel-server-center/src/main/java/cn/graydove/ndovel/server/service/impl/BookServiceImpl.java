package cn.graydove.ndovel.server.service.impl;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.model.dto.BookPageDTO;
import cn.graydove.ndovel.server.model.entity.Author;
import cn.graydove.ndovel.server.model.entity.Book;
import cn.graydove.ndovel.server.model.entity.Category;
import cn.graydove.ndovel.server.model.request.BookRequest;
import cn.graydove.ndovel.server.model.vo.BookVO;
import cn.graydove.ndovel.server.repository.AuthorRepository;
import cn.graydove.ndovel.server.repository.BookRepository;
import cn.graydove.ndovel.server.repository.CategoryRepository;
import cn.graydove.ndovel.server.service.BookService;
import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
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
        book = bookRepository.save(book);
        return book.getId();
    }

    @Override
    public Paging<BookVO> pageBook(BookPageDTO bookPageDTO) {
        return Paging.ofWithMap(bookRepository.findAll(bookPageDTO.toPageable()), book->BeanUtil.toBean(book, BookVO.class));
    }
}
