package cn.graydove.ndovel.server.center.service.impl;

import cn.graydove.ndovel.common.properties.NovelProperties;
import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.contant.ServerTopic;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.api.model.request.*;
import cn.graydove.ndovel.server.center.model.entity.Author;
import cn.graydove.ndovel.server.center.model.entity.Book;
import cn.graydove.ndovel.server.center.model.entity.Category;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.center.repository.AuthorRepository;
import cn.graydove.ndovel.server.center.repository.BookRepository;
import cn.graydove.ndovel.server.center.repository.CategoryRepository;
import cn.graydove.ndovel.server.center.service.BookService;
import cn.graydove.ndovel.server.center.service.NovelService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
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

    private RocketMQTemplate rocketMqTemplate;


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
        return Paging.ofWithMap(bookPage, book->BeanUtil.toBean(book, BookVO.class));
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

    private void publishBook(Book book) {
        NovelPutRequest novelPutRequest = new NovelPutRequest();
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

        //广播新建信息
        rocketMqTemplate.convertAndSend(ServerTopic.TOPIC_PUBLISH_NOVEL, novelPutRequest);
    }
}
