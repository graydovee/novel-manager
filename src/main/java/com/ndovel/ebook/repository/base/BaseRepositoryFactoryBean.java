package com.ndovel.ebook.repository.base;

import com.ndovel.ebook.model.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

@SuppressWarnings("unchecked")
public class BaseRepositoryFactoryBean<T extends JpaRepository<DOMAIN, ID>, DOMAIN extends BaseEntity, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, DOMAIN, ID> {

    public BaseRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(final EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager) {

            protected SimpleJpaRepository<DOMAIN, ID> getTargetRepository(
                    RepositoryInformation information,    EntityManager entityManager) {
                return new BaseRepositoryImpl( information.getDomainType(), entityManager);
            }

            @Override
            protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
                return BaseRepositoryImpl.class;
            }
        };
    }
}
