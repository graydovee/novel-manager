package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.Chapter;
import com.ndovel.ebook.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChapterRepository extends BaseRepository<Chapter, Integer>, JpaSpecificationExecutor<Chapter> {
}
