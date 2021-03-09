package com.example.app.repository.base;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class MyRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public MyRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                            EntityManager entityManager) {
        super(entityInformation, entityManager);


        this.entityManager = entityManager;
    }

    @Transactional
    public <S extends T> S save(S entity) {

        System.out.println("Called MyRepositoryImpl save method");

        return super.save(entity);
    }

}
