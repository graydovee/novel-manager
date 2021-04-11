package cn.graydove.ndovel.server.service.impl;

import cn.graydove.common.exception.TaskException;
import cn.graydove.common.response.Paging;
import cn.graydove.ndovel.server.model.document.ChapterDetail;
import cn.graydove.ndovel.server.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.server.model.entity.Book;
import cn.graydove.ndovel.server.model.entity.Chapter;
import cn.graydove.ndovel.server.model.request.ChapterRequest;
import cn.graydove.ndovel.server.model.vo.ChapterVO;
import cn.graydove.ndovel.server.repository.BookRepository;
import cn.graydove.ndovel.server.repository.ChapterDetailRepository;
import cn.graydove.ndovel.server.repository.ChapterRepository;
import cn.graydove.ndovel.server.service.ChapterService;
import cn.hutool.core.bean.BeanUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
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
        chapterDetail.setTitle(chapterRequest.getTitle());
        chapterDetail.setContent(chapterRequest.getContent());
        chapterDetailRepository.save(chapterDetail);
        return newLastChapter.getId();
    }

    @Override
    public ChapterVO findDetail(Long chapterId) {
        ChapterVO chapterVO = new ChapterVO();
        chapterRepository.findById(chapterId).ifPresent(chapter -> BeanUtil.copyProperties(chapter, chapterVO));
        chapterDetailRepository.findByChapterId(chapterId)
                .ifPresent(chapterDetail -> chapterVO.setContent(chapterDetail.getContent()));
        return chapterVO;
    }

    @Override
    public Paging<ChapterVO> pageByBookId(ChapterPageDTO chapterPageDTO) {
        Page<Chapter> chapterPage = chapterRepository.findByBookId(chapterPageDTO.getBookId(), chapterPageDTO.toPageable());
        if (Boolean.TRUE.equals(chapterPageDTO.getQueryContent())) {
            Map<Long, ChapterDetail> contentMap = chapterDetailRepository.findByBookId(chapterPageDTO.getBookId()).stream()
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
