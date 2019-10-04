package com.ndovel.ebook.repository;

import com.ndovel.ebook.model.entity.SpiderInfo;
import com.ndovel.ebook.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpiderInfoRepository extends BaseRepository<SpiderInfo>, JpaSpecificationExecutor<SpiderInfo> {
}
