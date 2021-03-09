package com.example.app.repository.querybyexample;

import com.example.app.domain.Person;
import com.example.app.repository.base.MyBaseRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PersonQueryByExampleRepository extends
        MyBaseRepository<Person, Long>,
        QueryByExampleExecutor<Person> {
}
