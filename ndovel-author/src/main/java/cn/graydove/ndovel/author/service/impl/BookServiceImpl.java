package cn.graydove.ndovel.author.service.impl;

import cn.graydove.ndovel.author.model.dto.*;
import cn.graydove.ndovel.author.service.AssertService;
import cn.graydove.ndovel.author.service.BookService;
import cn.graydove.ndovel.client.UserContext;
import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.common.query.PageQuery;
import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.api.facade.BookReadFacade;
import cn.graydove.ndovel.server.api.facade.BookWriteFacade;
import cn.graydove.ndovel.server.api.model.request.*;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collections;

/**
 * @author graydove
 */
@Service
public class BookServiceImpl implements BookService {

    @DubboReference
    private BookWriteFacade bookWriteFacade;

    @DubboReference
    private BookReadFacade bookReadFacade;

    private AssertService assertService;

    public BookServiceImpl(AssertService assertService) {
        this.assertService = assertService;
    }

    @Override
    public Paging<BookVO> listReviewFailBook(PageQuery pageQuery) {
        BookPageRequest request = BeanUtil.toBean(pageQuery, BookPageRequest.class);
        request.setStatuses(Collections.singleton(PublishStatus.SAVE));
        return bookReadFacade.pageBook(request);
    }

    @Override
    public Paging<ChapterVO> listReviewFailChapter(PageChapterDTO pageChapterDTO) {
        if (null == pageChapterDTO.getBookId()) {
            ChapterPageAllRequest request = BeanUtil.toBean(pageChapterDTO, ChapterPageAllRequest.class);
            request.setStatuses(Collections.singleton(PublishStatus.SAVE));
            request.setQueryContent(true);
            return bookReadFacade.pageAllChapter(request);
        } else {
            ChapterPageRequest request = BeanUtil.toBean(pageChapterDTO, ChapterPageRequest.class);
            request.setStatuses(Collections.singleton(PublishStatus.SAVE));
            request.setQueryContent(true);
            return bookReadFacade.pageChapter(request);
        }
    }

    @Override
    public Long createBook(BookDTO bookDTO) {
        BookRequest bookRequest = BeanUtil.toBean(bookDTO, BookRequest.class);
        UserVO user = UserContext.getUserOrEx();
        bookRequest.setAuthorId(user.getId());
        bookRequest.setAuthor(user.getNickname());
        bookRequest.setStatus(PublishStatus.SAVE);
        return bookWriteFacade.createBook(bookRequest);
    }

    @Override
    public Long appendChapter(ChapterDTO chapterDTO) {
        assertService.assertBookIsOwner(chapterDTO.getBookId());
        ChapterRequest chapterRequest = BeanUtil.toBean(chapterDTO, ChapterRequest.class);
        chapterRequest.setStatus(PublishStatus.SAVE);
        return bookWriteFacade.appendChapter(chapterRequest);
    }

    @Override
    public Boolean updateBook(UpdateBookDTO updateBookDTO) {
        assertService.assertBookIsOwner(updateBookDTO.getId());
        UpdateBookRequest request = BeanUtil.toBean(updateBookDTO, UpdateBookRequest.class);
        request.setStatus(PublishStatus.SAVE);
        return bookWriteFacade.updateBook(request);
    }

    @Override
    public Boolean updateChapter(UpdateChapterDTO updateChapterDTO) {
        assertService.assertChapterIsOwner(updateChapterDTO.getId());
        UpdateChapterRequest request = BeanUtil.toBean(updateChapterDTO, UpdateChapterRequest.class);
        request.setStatus(PublishStatus.SAVE);
        return bookWriteFacade.updateChapter(request);
    }

    @Override
    public Boolean submitBook(SubmitDTO submitDTO) {
        Long id = submitDTO.getId();
        assertService.assertBookIsOwner(id);
        UpdateBookRequest request = new UpdateBookRequest();
        request.setId(id);
        request.setStatus(PublishStatus.REVIEW);
        return bookWriteFacade.updateBook(request);
    }

    @Override
    public Boolean submitChapter(SubmitDTO submitDTO) {
        Long id = submitDTO.getId();
        assertService.assertChapterIsOwner(id);
        UpdateChapterRequest request = new UpdateChapterRequest();
        request.setId(id);
        request.setStatus(PublishStatus.REVIEW);
        return bookWriteFacade.updateChapter(request);
    }

}