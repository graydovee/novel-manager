package cn.graydove.ebook.web.repository;

import cn.graydove.ebook.web.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("select count(id) from Author where name=?1")
    Integer countAuthorByName(String name);
}
