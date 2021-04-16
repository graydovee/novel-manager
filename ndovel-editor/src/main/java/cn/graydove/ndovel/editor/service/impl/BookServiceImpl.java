package cn.graydove.ndovel.editor.service.impl;

import cn.graydove.ndovel.common.query.PageQuery;
import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.editor.model.dto.AuditDTO;
import cn.graydove.ndovel.editor.model.dto.ChapterDTO;
import cn.graydove.ndovel.editor.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.editor.service.BookService;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.api.facade.BookReadFacade;
import cn.graydove.ndovel.server.api.facade.BookWriteFacade;
import cn.graydove.ndovel.server.api.model.request.*;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import cn.hutool.core.bean.BeanUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author graydove
 */
@Service
public class BookServiceImpl implements BookService {

    @DubboReference
    private BookReadFacade bookReadFacade;

    @DubboReference
    private BookWriteFacade bookWriteFacade;

    @Override
    public Paging<BookVO> pageReviewBook(PageQuery pageQuery) {
        BookPageRequest request = BeanUtil.toBean(pageQuery, BookPageRequest.class);
        request.setStatuses(Collections.singleton(PublishStatus.REVIEW));
        return bookReadFacade.pageBook(request);
    }

    @Override
    public Paging<ChapterVO> pageReviewChapter(ChapterPageDTO chapterPageDTO) {
        ChapterPageRequest request = BeanUtil.toBean(chapterPageDTO, ChapterPageRequest.class);
        request.setStatuses(Collections.singleton(PublishStatus.REVIEW));
        return bookReadFacade.pageChapter(request);
    }

    @Override
    public ChapterVO findChapterById(ChapterDTO chapterDTO) {
        ChapterIdRequest request = new ChapterIdRequest();
        request.setChapterId(chapterDTO.getChapterId());
        request.setPublishStatus(PublishStatus.REVIEW);
        return bookReadFacade.findChapter(request);
    }

    @Override
    public Boolean auditBook(AuditDTO auditDTO) {
        UpdateBookRequest request = new UpdateBookRequest();
        request.setId(auditDTO.getId());
        request.setStatus(auditDTO.getResult() ? PublishStatus.RELEASE : PublishStatus.SAVE);
        return bookWriteFacade.updateBook(request);
    }

    @Override
    public Boolean auditChapter(AuditDTO auditDTO) {
        UpdateChapterRequest request = new UpdateChapterRequest();
        request.setId(auditDTO.getId());
        request.setStatus(auditDTO.getResult() ? PublishStatus.RELEASE : PublishStatus.SAVE);
        return bookWriteFacade.updateChapter(request);
    }
}
