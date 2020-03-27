package com.ndovel.ebook.repository.base;

import com.ndovel.ebook.model.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@NoRepositoryBean
public interface BaseRepository<DOMAIN extends BaseEntity> extends JpaRepository<DOMAIN, Integer> {


    List<DOMAIN> findAllIsExist(boolean isDesc);

    default List<DOMAIN> findAllIsExist() {
        return findAllIsExist(false);
    }

    Optional<DOMAIN> findOneIsExist(Integer id);

    DOMAIN refresh(DOMAIN domain);

    Long countIsExist();

    Page<DOMAIN> findIsExist(Specification<DOMAIN> spec, Pageable pageable, boolean isDesc);

    default Page<DOMAIN> findIsExist(Specification<DOMAIN> spec, Pageable pageable) {
        return findIsExist(spec, pageable, false);
    }

    Page<DOMAIN> findIsExist(Pageable pageable, boolean isDesc);

    default Page<DOMAIN> findIsExist(Pageable pageable) {
        return findIsExist(pageable, false);
    }

    Long countIsExistById(Integer id);
}
