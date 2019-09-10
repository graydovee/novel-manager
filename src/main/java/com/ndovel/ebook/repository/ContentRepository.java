package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.Content;
import com.ndovel.ebook.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContentRepository extends BaseRepository<Content>, JpaSpecificationExecutor<Content> {
}
