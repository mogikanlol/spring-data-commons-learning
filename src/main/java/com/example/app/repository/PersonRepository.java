package com.example.app.repository;

import com.example.app.domain.Person;
import com.example.app.repository.base.MyBaseRepository;
import com.example.app.repository.customized.CustomizedPersonRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface PersonRepository extends
        MyBaseRepository<Person, Long>,
        PagingAndSortingRepository<Person, Long>,
        CustomizedPersonRepository {

    List<Person> deleteByLastnameIgnoreCase(String lastname);

    @Query("SELECT p FROM Person p WHERE p.firstname = :firstname AND p.lastname = :lastname")
    Optional<Person> findByFirstnameAndLastname(@Param("firstname") String firstname, @Param("lastname") String lastname);


    @Async
    @Query("SELECT p FROM Person p WHERE p.firstname = :firstname AND p.lastname = :lastname")
    CompletableFuture<Optional<Person>> findByFirstnameAndLastnameAsync(
            @Param("firstname") String firstname,
            @Param("lastname") String lastname
    );


}
