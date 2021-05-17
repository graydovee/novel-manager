package cn.graydove.ndovel.server.center.service.impl;

import cn.graydove.ndovel.common.properties.NovelProperties;
import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.api.model.request.*;
import cn.graydove.ndovel.server.api.model.vo.CategoryVO;
import cn.graydove.ndovel.server.center.model.entity.Author;
import cn.graydove.ndovel.server.center.model.entity.Book;
import cn.graydove.ndovel.server.center.model.entity.Category;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.center.repository.*;
import cn.graydove.ndovel.server.center.service.BookService;
import cn.graydove.ndovel.server.center.service.NovelService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
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

    private NovelProperties novelProperties;

    private NovelService novelService;

    private NovelRepository novelRepository;

    private ChapterRepository chapterRepository;

    private ChapterDetailRepository chapterDetailRepository;


    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public Long createBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setIntroduce(bookRequest.getIntroduce());
        Author author = authorRepository.findByName(bookRequest.getAuthor())
                .orElseGet(() -> {
                    Author a = new Author();
                    a.setName(bookRequest.getAuthor());
                    a.setUserId(bookRequest.getAuthorId());
                    return authorRepository.save(a);
                });
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
        if (book.getStatus() == PublishStatus.RELEASE) {
            publishBook(book);
        }
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
        return Paging.ofWithMap(bookPage, book-> {
            BookVO bookVO = BeanUtil.toBean(book, BookVO.class);
            bookVO.setType(book.getCategory().stream()
                    .map(c -> BeanUtil.toBean(c, CategoryVO.class))
                    .collect(Collectors.toList())
            );
            return bookVO;
        });
    }

    @Override
    @Transactional(rollbackFor = {Throwable.class})
    public Boolean updateBook(UpdateBookRequest updateBookRequest) {
        return bookRepository.findById(updateBookRequest.getId()).map(book -> {
            BeanUtil.copyProperties(updateBookRequest, book, CopyOptions.create(null, true));
            Book save = bookRepository.save(book);
            if (save.getStatus() == PublishStatus.RELEASE) {
                publishBook(book);
            }
            return true;
        }).orElse(false);
    }

    @Override
    public BookVO findBook(BookIdRequest bookIdRequest) {
        Optional<Book> book = null == bookIdRequest.getPublishStatus() ?
                bookRepository.findById(bookIdRequest.getBookId()) :
                bookRepository.findByIdAndStatus(bookIdRequest.getBookId(), bookIdRequest.getPublishStatus());

        return book.map(b -> BeanUtil.toBean(b, BookVO.class))
                .orElse(null);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean deleteBook(BookDeleteRequest bookDeleteRequest) {
        Long id = bookDeleteRequest.getBookId();
        Book book = bookRepository.findById(id).orElse(null);
        if (null == book) {
            return false;
        }
        bookRepository.deleteById(id);
        chapterRepository.deleteByBookId(id);
        chapterDetailRepository.deleteByBookId(id);
        novelRepository.deleteByBookId(id);
        return true;
    }

    private void publishBook(Book book) {
        NovelPutRequest novelPutRequest = new NovelPutRequest();

        Long id = book.getId();
        if (Boolean.TRUE.equals(novelService.exist(id))) {
            novelService.updateNovel(id, novelDO -> {
                novelDO.setName(book.getName());
                novelDO.setIntroduce(book.getIntroduce());
                novelDO.setCover(book.getCover());
                return novelDO;
            });
        } else {
            novelPutRequest.setBookId(book.getId());
            novelPutRequest.setName(book.getName());
            novelPutRequest.setAuthor(book.getAuthor().getName());
            novelPutRequest.setCover(book.getCover());
            novelPutRequest.setIntroduce(book.getIntroduce());
            novelPutRequest.setType(book.getCategory().stream().map(Category::getName).collect(Collectors.toSet()));
            novelPutRequest.setFrom(novelProperties.getAddress());
            novelPutRequest.setVisit(0L);
            novelPutRequest.setCreateTime(new Date(LocalDateTimeUtil.toEpochMilli(book.getCreateTime())));
            novelPutRequest.setUpdateTime(new Date(LocalDateTimeUtil.toEpochMilli(book.getUpdateTime())));

            //写入本机es
            novelService.saveNovel(novelPutRequest);
        }
    }
}
