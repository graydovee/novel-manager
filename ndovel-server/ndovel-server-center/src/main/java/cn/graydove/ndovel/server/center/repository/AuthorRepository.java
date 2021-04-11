package cn.graydove.ndovel.server.center.repository;

import cn.graydove.ndovel.server.center.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author graydove
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);
}
