package cn.graydove.ndovel.reader.service.impl;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.reader.model.dto.BookPageDTO;
import cn.graydove.ndovel.reader.model.dto.ChapterIdDTO;
import cn.graydove.ndovel.reader.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.reader.model.dto.NovelSearchDTO;
import cn.graydove.ndovel.reader.service.BookService;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.api.facade.BookReadFacade;
import cn.graydove.ndovel.server.api.facade.NovelFacade;
import cn.graydove.ndovel.server.api.model.request.BookPageRequest;
import cn.graydove.ndovel.server.api.model.request.ChapterIdRequest;
import cn.graydove.ndovel.server.api.model.request.ChapterPageRequest;
import cn.graydove.ndovel.server.api.model.request.NovelSearchRequest;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import cn.graydove.ndovel.server.api.model.vo.NovelVO;
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
    private NovelFacade novelFacade;

    @Override
    public ChapterVO findChapter(ChapterIdDTO chapterIdDTO) {
        ChapterIdRequest chapterIdRequest = BeanUtil.toBean(chapterIdDTO, ChapterIdRequest.class);
        chapterIdRequest.setPublishStatus(PublishStatus.RELEASE);
        return bookReadFacade.findChapter(chapterIdRequest);
    }

    @Override
    public Paging<ChapterVO> pageChapter(ChapterPageDTO chapterPageDTO) {
        ChapterPageRequest request = BeanUtil.toBean(chapterPageDTO, ChapterPageRequest.class);
        request.setStatuses(Collections.singleton(PublishStatus.RELEASE));
        return bookReadFacade.pageChapter(request);
    }

    @Override
    public Paging<BookVO> pageBook(BookPageDTO bookPageDTO) {
        BookPageRequest request = BeanUtil.toBean(bookPageDTO, BookPageRequest.class);
        request.setStatuses(Collections.singleton(PublishStatus.RELEASE));
        return bookReadFacade.pageBook(request);
    }

    @Override
    public Paging<NovelVO> searchNovel(NovelSearchDTO novelSearchDTO) {
        NovelSearchRequest request = BeanUtil.toBean(novelSearchDTO, NovelSearchRequest.class);
        return novelFacade.searchNovel(request);
    }
}
