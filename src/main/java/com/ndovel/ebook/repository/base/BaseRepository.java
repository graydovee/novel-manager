package com.ndovel.ebook.repository.base;

import com.ndovel.ebook.model.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<DOMAIN extends BaseEntity, ID extends Serializable> extends JpaRepository<DOMAIN, ID> {

    List<DOMAIN> findAllIsExist();

    Optional<DOMAIN> findOneIsExist(ID id);

}
