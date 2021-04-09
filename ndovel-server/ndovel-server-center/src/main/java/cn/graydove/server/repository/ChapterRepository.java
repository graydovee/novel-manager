package cn.graydove.server.repository;

import cn.graydove.server.model.entity.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author graydove
 */
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Page<Chapter> findByBookId(Long bookId, Pageable pageable);
}
