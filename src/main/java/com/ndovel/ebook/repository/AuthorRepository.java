package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.Author;
import com.ndovel.ebook.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthorRepository extends BaseRepository<Author, Integer>, JpaSpecificationExecutor<Author> {
}
