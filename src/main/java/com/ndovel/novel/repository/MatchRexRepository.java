package com.ndovel.novel.repository;

import com.ndovel.novel.model.entity.MatchRex;
import com.ndovel.novel.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRexRepository extends BaseRepository<MatchRex>, JpaSpecificationExecutor<MatchRex> {

    @Query("from MatchRex where name=?1 and deleted=0")
    MatchRex findOneByName(String name);

    @Query("from MatchRex where name like %?1% and deleted=0")
    List<MatchRex> findAllByName(String name);
}
