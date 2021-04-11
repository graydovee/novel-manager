package cn.graydove.ndovel.server.repository;

import cn.graydove.ndovel.server.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author graydove
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
