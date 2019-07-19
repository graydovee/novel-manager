package cn.graydove.ebook.web.repository;

import cn.graydove.ebook.web.model.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    @Query("from Chapter where thisPage=?1 and bookId=?2")
    Chapter selChapterByThisPage(String page, Integer bookId);

    @Query("select chapter.thisPage, chapter.title from Chapter chapter where bookId=?1")
    List<Object[]> findAllChapterByBookId(Integer bookId);

}
