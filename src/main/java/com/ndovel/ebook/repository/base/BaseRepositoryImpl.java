package com.ndovel.ebook.repository.base;

import com.ndovel.ebook.exception.DataIsNotExistException;
import com.ndovel.ebook.model.entity.base.BaseEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<DOMAIN extends BaseEntity>
        extends SimpleJpaRepository<DOMAIN, Integer>
        implements BaseRepository<DOMAIN> {

    private final EntityManager entityManager; //父类没有不带参数的构造方法，这里手动构造父类

    public BaseRepositoryImpl(Class<DOMAIN> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public void delete(DOMAIN entity) {
        entity.setDeleted(true);
        save(entity);
    }

    @Override
    public void deleteById(Integer id){
        findById(id).ifPresent(domain -> {
            domain.setDeleted(true);
            save(domain);
        });
    }


    @Override
    public List<DOMAIN> findAllIsExist() {
        Specification<DOMAIN> spec = new Specification<DOMAIN>() {
            @Override
            public Predicate toPredicate(Root<DOMAIN> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> deleted = root.get("deleted");

                return criteriaBuilder.equal(deleted, 0);
            }
        };
        return findAll(spec);
    }




    @Override
    public Optional<DOMAIN> findOneIsExist(Integer id) {
        Specification<DOMAIN> spec = new Specification<DOMAIN>() {
            @Override
            public Predicate toPredicate(Root<DOMAIN> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Object> deleted = root.get("deleted");
                Path<Object> idp = root.get("id");

                Predicate p1 = criteriaBuilder.equal(deleted, 0);
                Predicate p2 = criteriaBuilder.equal(idp, id);

                return criteriaBuilder.and(p1, p2);
            }
        };
        return findOne(spec);
    }
}
