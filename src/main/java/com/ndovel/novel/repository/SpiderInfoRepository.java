package com.ndovel.novel.repository;

import com.ndovel.novel.model.entity.SpiderInfo;
import com.ndovel.novel.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpiderInfoRepository extends BaseRepository<SpiderInfo>, JpaSpecificationExecutor<SpiderInfo> {

    @Modifying
    @Query("update SpiderInfo set finished=1 where id=?1")
    Integer setFinishedTrueById(Integer id);

    @Modifying
    @Query("update SpiderInfo set finished=0 where id=?1")
    Integer setFinishedFalseById(Integer id);

    @Query("from SpiderInfo where finished=0 and deleted=0")
    List<SpiderInfo> findAllNotFinish();

    @Modifying
    @Query("update SpiderInfo s set s.deleted=1 where s.book.id=?1")
    Integer deleteByBookId(Integer bookId);
}
