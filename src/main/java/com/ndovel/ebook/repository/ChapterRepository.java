package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.Chapter;
import com.ndovel.ebook.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterRepository extends BaseRepository<Chapter>, JpaSpecificationExecutor<Chapter> {

    @Query("from Chapter where bookId=?1 and deleted=0")
    List<Chapter> findAllByBookId(Integer bookId);

    @Modifying
    @Query("update Chapter set deleted=1 where bookId=?1")
    void deleteAllByBookId(Integer bookId);
}
