package cn.graydove.ebook.web.repository;

import cn.graydove.ebook.web.model.entity.Chapter;
import cn.graydove.ebook.web.model.vo.ChapterVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    @Query("from Chapter where thisPage=?1 and bookId=?2")
    Chapter selChapterByThisPage(String page, Integer bookId);

    @Query("select new cn.graydove.ebook.web.model.vo.ChapterVO(chapter.id,chapter.title,chapter.bookId,chapter.thisPage,chapter.nextPage) from Chapter chapter where bookId=?1")
    List<ChapterVO> findAllChapterByBookId(Integer bookId);

}
