package cn.graydove.server.service.impl;

import cn.graydove.common.exception.TaskException;
import cn.graydove.server.model.document.ChapterDetail;
import cn.graydove.server.model.entity.Book;
import cn.graydove.server.model.entity.Chapter;
import cn.graydove.server.model.request.ChapterRequest;
import cn.graydove.server.model.vo.ChapterVO;
import cn.graydove.server.repository.BookRepository;
import cn.graydove.server.repository.ChapterDetailRepository;
import cn.graydove.server.repository.ChapterRepository;
import cn.graydove.server.service.ChapterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        chapterDetail.setBookId(book.getId());
        chapterDetail.setTitle(chapterRequest.getTitle());
        chapterDetail.setContent(chapterRequest.getContent());
        chapterDetailRepository.save(chapterDetail);
        return newLastChapter.getId();
    }

    @Override
    public ChapterVO find(Long chapterId) {
        ChapterVO chapterVO = new ChapterVO();
        chapterRepository.findById(chapterId).ifPresent(chapter -> {
            chapterVO.setTitle(chapter.getTitle());
            chapterVO.setNextChapterId(chapter.getNextChapterId());
            chapterVO.setPreChapterId(chapter.getPreChapterId());
            chapterVO.setId(chapter.getId());
            chapterVO.setUpdateTime(chapter.getUpdateTime());
            chapterVO.setCreateTime(chapter.getCreateTime());
        });
        chapterDetailRepository.findByChapterId(chapterId)
                .ifPresent(chapterDetail -> chapterVO.setContent(chapterDetail.getContent()));
        return chapterVO;
    }
}
