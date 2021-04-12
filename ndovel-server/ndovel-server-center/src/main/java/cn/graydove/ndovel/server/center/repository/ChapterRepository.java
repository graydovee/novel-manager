package cn.graydove.ndovel.server.center.repository;

import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.center.model.entity.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author graydove
 */
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Optional<Chapter> findByIdAndStatus(Long bookId, PublishStatus status);

    Page<Chapter> findByBookId(Long bookId, Pageable pageable);

    Page<Chapter> findByBookIdAndStatusIn(Long bookId, Collection<PublishStatus> statuses, Pageable pageable);
}
