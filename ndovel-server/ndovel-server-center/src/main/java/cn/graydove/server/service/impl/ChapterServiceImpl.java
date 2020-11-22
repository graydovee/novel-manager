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

@Slf4j
@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private ChapterDetailRepository chapterDetailRepository;

    private ChapterRepository chapterRepository;

    private BookRepository bookRepository;

    @Override
    @Transactional
    public Long appendChapter(ChapterRequest chapterRequest) {
        Book book = bookRepository.findById(chapterRequest.getBookId()).orElseThrow(() -> new TaskException("bookId无效"));
        log.info("[ append chapter ] {}: {}", book.getName(), chapterRequest.getTitle());
        Optional<Chapter> lastChapter = Optional.ofNullable(book.getLastChapterId()).flatMap(chapterRepository::findById);

        Chapter chapter = new Chapter();
        chapter.setTitle(chapterRequest.getTitle());
        chapter.setPreChapterId(lastChapter.map(Chapter::getId).orElse(null));
        Chapter savedChapter = chapterRepository.save(chapter);

        ChapterDetail chapterDetail = new ChapterDetail();
        chapterDetail.setChapterId(savedChapter.getId());
        chapterDetail.setBookId(book.getId());
        chapterDetail.setTitle(chapterRequest.getTitle());
        chapterDetail.setContent(chapterRequest.getContent());
        chapterDetailRepository.save(chapterDetail);

        lastChapter.ifPresent(chp -> {
            chp.setNextChapterId(savedChapter.getId());
            chapterRepository.save(chp);
        });
        if (book.getFirstChapterId() == null) {
            book.setFirstChapterId(chapter.getId());
        }
        book.setLastChapterId(chapter.getId());
        return chapter.getId();
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
