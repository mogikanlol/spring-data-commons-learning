package com.example.app.repository;

import com.example.app.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PersonRepository extends
        CrudRepository<Person, Long>,
        PagingAndSortingRepository<Person, Long> {

    long countByLastname(String lastname);

    List<Person> deleteByLastnameIgnoreCase(String lastname);

    List<Person> removeByLastname(String lastname);
}
