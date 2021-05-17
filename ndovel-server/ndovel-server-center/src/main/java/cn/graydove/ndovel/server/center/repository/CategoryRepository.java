package cn.graydove.ndovel.server.center.repository;

import cn.graydove.ndovel.server.center.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @author graydove
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}