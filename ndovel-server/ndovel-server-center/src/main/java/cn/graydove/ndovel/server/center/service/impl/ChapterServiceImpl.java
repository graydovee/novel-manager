package cn.graydove.ndovel.server.center.service.impl;

import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.api.model.request.*;
import cn.graydove.ndovel.server.center.model.document.ChapterDetail;
import cn.graydove.ndovel.server.center.model.entity.Book;
import cn.graydove.ndovel.server.center.model.entity.Chapter;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import cn.graydove.ndovel.server.center.model.entity.base.BaseEntity;
import cn.graydove.ndovel.server.center.repository.BookRepository;
import cn.graydove.ndovel.server.center.repository.ChapterDetailRepository;
import cn.graydove.ndovel.server.center.repository.ChapterRepository;
import cn.graydove.ndovel.server.center.service.ChapterService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author graydove
 */
@Slf4j
@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private ChapterDetailRepository chapterDetailRepository;

    private ChapterRepository chapterRepository;

    private BookRepository bookRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long appendChapter(ChapterRequest chapterRequest) {
        Book book = bookRepository.findById(chapterRequest.getBookId()).orElseThrow(() -> new TaskException("bookId无效"));
        log.info("[ append chapter ] {}: {}", book.getName(), chapterRequest.getTitle());
        Optional<Chapter> oldLastChapter = Optional.ofNullable(book.getLastChapterId()).flatMap(chapterRepository::findById);

        Chapter newLastChapter = new Chapter();
        newLastChapter.setBookId(chapterRequest.getBookId());
        newLastChapter.setTitle(chapterRequest.getTitle());
        newLastChapter.setStatus(chapterRequest.getStatus());
        newLastChapter.setPreChapterId(oldLastChapter.map(Chapter::getId).orElse(null));
        Chapter savedChapter = chapterRepository.save(newLastChapter);

        //旧终章的下一章更新为此章
        oldLastChapter.ifPresent(chp -> {
            chp.setNextChapterId(savedChapter.getId());
            chapterRepository.save(chp);
        });

        //更新小说信息的首章和终章索引
        if (book.getFirstChapterId() == null) {
            book.setFirstChapterId(newLastChapter.getId());
        }
        book.setLastChapterId(newLastChapter.getId());
        bookRepository.save(book);

        //保存章节内容
        ChapterDetail chapterDetail = new ChapterDetail();
        chapterDetail.setChapterId(savedChapter.getId());
        chapterDetail.setBookId(chapterRequest.getBookId());
        chapterDetail.setContent(chapterRequest.getContent());
        chapterDetailRepository.save(chapterDetail);
        return newLastChapter.getId();
    }

    @Override
    public ChapterVO findDetail(ChapterIdRequest chapterIdRequest) {
        Long chapterId = chapterIdRequest.getChapterId();
        PublishStatus publishStatus = Optional.ofNullable(chapterIdRequest.getPublishStatus()).orElse(PublishStatus.RELEASE);
        return chapterRepository.findByIdAndStatus(chapterId, publishStatus)
                .map(chapter -> {
                    ChapterVO chapterVO = BeanUtil.toBean(chapter, ChapterVO.class);
                    chapterDetailRepository.findByChapterId(chapterId)
                            .ifPresent(chapterDetail -> chapterVO.setContent(chapterDetail.getContent()));
                    return chapterVO;
                }).orElse(null);
    }

    @Override
    public Paging<ChapterVO> pageByBookId(ChapterPageRequest chapterPageRequest) {
        //查询章节信息
        Collection<PublishStatus> statuses = chapterPageRequest.getStatuses();
        Page<Chapter> chapterPage;
        if (CollectionUtil.isEmpty(statuses)) {
            chapterPage = chapterRepository.findByBookId(chapterPageRequest.getBookId(), chapterPageRequest.toPageable());
        } else {
            chapterPage = chapterRepository.findByBookIdAndStatusIn(chapterPageRequest.getBookId(), statuses, chapterPageRequest.toPageable());
        }
        return toChapterVO(chapterPage, chapterPageRequest.getQueryContent());
    }

    @Override
    public Paging<ChapterVO> pageAll(ChapterPageAllRequest chapterPageAllRequest) {
        Page<Chapter> chapterPage;
        if (CollectionUtil.isEmpty(chapterPageAllRequest.getStatuses())) {
            chapterPage = chapterRepository.findAll(chapterPageAllRequest.toPageable());
        } else {
            chapterPage = chapterRepository.findByStatusIn(chapterPageAllRequest.getStatuses(), chapterPageAllRequest.toPageable());
        }
        return toChapterVO(chapterPage, chapterPageAllRequest.getQueryContent());
    }

    @Override
    public Boolean updateChapter(UpdateChapterRequest updateChapterRequest) {
        boolean chp = false;
        boolean cnt = false;
        if (StrUtil.isNotBlank(updateChapterRequest.getTitle()) || null != updateChapterRequest.getStatus()) {
             chapterRepository.findById(updateChapterRequest.getId()).ifPresent(chapter -> {
                Optional.ofNullable(updateChapterRequest.getTitle()).filter(StrUtil::isNotBlank).ifPresent(chapter::setTitle);
                Optional.ofNullable(updateChapterRequest.getStatus()).ifPresent(chapter::setStatus);
                chapterRepository.save(chapter);
            });
            chp =  true;
        }
        if (StrUtil.isNotBlank(updateChapterRequest.getContent())) {
            chapterDetailRepository.findByChapterId(updateChapterRequest.getId()).ifPresent(chapterDetail -> {
                chapterDetail.setContent(updateChapterRequest.getContent());
                chapterDetailRepository.save(chapterDetail);
            });
            cnt = true;
        }
        return chp || cnt;
    }

    private Paging<ChapterVO> toChapterVO(Page<Chapter> chapterPage, Boolean withContent) {
        //查询章节正文内容
        if (Boolean.TRUE.equals(withContent)) {
            Set<Long> ids = chapterPage.stream().map(BaseEntity::getId).collect(Collectors.toSet());
            Map<Long, ChapterDetail> contentMap = chapterDetailRepository.findByChapterIdIn(ids).stream()
                    .collect(Collectors.toMap(ChapterDetail::getChapterId, v -> v));
            return Paging.ofWithStream(chapterPage,
                    chapterStream -> chapterStream
                            .map(chapter -> BeanUtil.toBean(chapter, ChapterVO.class))
                            .peek(chapterVO -> chapterVO.setContent(contentMap.get(chapterVO.getId()).getContent()))
                            .collect(Collectors.toList())
            );
        }
        return Paging.ofWithMap(chapterPage, chapter -> BeanUtil.toBean(chapter, ChapterVO.class));
    }
}
