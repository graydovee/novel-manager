package cn.graydove.server.repository;

import cn.graydove.server.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author graydove
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);
}
