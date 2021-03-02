package com.example.app.repository;

import com.example.app.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PersonQueryByExampleRepository extends
        CrudRepository<Person, Long>,
        QueryByExampleExecutor<Person> {
}
