package cn.graydove.ndovel.author.service;

import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;

import java.util.function.Function;

/**
 * @author graydove
 */
public interface AssertService {

    Boolean isBookOwner(Long id);

    Boolean isChapterOwner(Long id);

    void assertBookIsOwner(Long id);

    void assertChapterIsOwner(Long id);

    Boolean assertBook(Long bookId, Function<BookVO, Boolean> function);

    Boolean assertChapter(Long chapterId, Function<ChapterVO, Boolean> function);
}
