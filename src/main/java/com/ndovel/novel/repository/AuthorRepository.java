package com.ndovel.novel.repository;

import com.ndovel.novel.model.entity.Author;
import com.ndovel.novel.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends BaseRepository<Author>, JpaSpecificationExecutor<Author> {

    @Query("from Author where name=?1 and deleted=0")
    Author findOneByName(String name);
}
