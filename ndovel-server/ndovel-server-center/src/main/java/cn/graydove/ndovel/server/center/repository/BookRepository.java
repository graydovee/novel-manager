package cn.graydove.ndovel.server.center.repository;

import cn.graydove.ndovel.server.api.enums.PublishStatus;
import cn.graydove.ndovel.server.center.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author graydove
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIdAndStatus(Long id, PublishStatus publishStatus);

    Page<Book> findAllByStatusIn(Collection<PublishStatus> publishStatus, Pageable pageable);
}
