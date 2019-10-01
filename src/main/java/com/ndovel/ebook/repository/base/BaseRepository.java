package com.ndovel.ebook.repository.base;

import com.ndovel.ebook.model.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<DOMAIN extends BaseEntity> extends JpaRepository<DOMAIN, Integer> {

    List<DOMAIN> findAllIsExist();

    Optional<DOMAIN> findOneIsExist(Integer id);

    Optional<DOMAIN> refresh(DOMAIN domain);

    Long countIsExist();

    Page<DOMAIN> findIsExist(Specification<DOMAIN> spec, Pageable pageable);

    Page<DOMAIN> findIsExist(Pageable pageable);

    Long countIsExistById(Integer id);
}
