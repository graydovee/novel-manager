package cn.graydove.ndovel.server.repository;

import cn.graydove.ndovel.server.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author graydove
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);
}
