package com.ndovel.novel.repository;

import com.ndovel.novel.model.entity.Content;
import com.ndovel.novel.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ContentRepository extends BaseRepository<Content>, JpaSpecificationExecutor<Content> {

    @Modifying
    @Query(value = "update content set content.visit=content.visit+1 where id=?1", nativeQuery = true)
    void addVisit(Integer contentId);
}
