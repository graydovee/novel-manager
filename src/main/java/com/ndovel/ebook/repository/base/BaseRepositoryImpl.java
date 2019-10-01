package com.ndovel.ebook.repository.base;

import com.ndovel.ebook.model.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
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
        return findAll(new IsExist<>());
    }

    @Override
    public Long countIsExist(){
        return count(new IsExist<>());
    }

    @Override
    public Page<DOMAIN> findIsExist(Pageable pageable) {
        return findAll(new IsExist<>(), pageable);
    }

    @Override
    public Page<DOMAIN> findIsExist(Specification<DOMAIN> spec, Pageable pageable) {

        return findAll(and(new IsExist<>(),spec),pageable);

    }


    @Override
    public Long countIsExistById(Integer id){
        return count(new IdAndIsExist<>());
    }


    @Override
    public Optional<DOMAIN> findOneIsExist(Integer id) {
        return findOne(new IdAndIsExist<>());
    }

    @Override
    public Optional<DOMAIN> refresh(DOMAIN domain) {
        domain.setDeleted(false);
        save(domain);
        return Optional.of(domain);
    }

    private class IsExist<T extends BaseEntity> implements Specification<T>{

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            Path<Object> deleted = root.get("deleted");

            return criteriaBuilder.equal(deleted, 0);
        }
    }

    private class IdAndIsExist<T extends BaseEntity> implements Specification<T>{

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            Path<Object> deleted = root.get("deleted");
            Path<Object> idp = root.get("id");

            Predicate p1 = criteriaBuilder.equal(deleted, 0);
            Predicate p2 = criteriaBuilder.equal(idp, idp);

            return criteriaBuilder.and(p1, p2);
        }
    }

    private Specification<DOMAIN> and(Specification<DOMAIN> s1, Specification<DOMAIN> s2){
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .and(s1.toPredicate(root, query, criteriaBuilder),
                        s2.toPredicate(root, query, criteriaBuilder));
    }
}
