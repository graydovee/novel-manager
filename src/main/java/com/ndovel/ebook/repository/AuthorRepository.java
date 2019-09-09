package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.Author;
import com.ndovel.ebook.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends BaseRepository<Author, Integer>, JpaSpecificationExecutor<Author> {

    @Query("from Author where name=?1 and deleted=0")
    Author findOneByName(String name);
}
