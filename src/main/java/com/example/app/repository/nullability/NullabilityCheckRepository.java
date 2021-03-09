package com.example.app.repository.nullability;

import com.example.app.domain.Person;
import com.example.app.repository.base.MyBaseRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NullabilityCheckRepository extends MyBaseRepository<Person, Long> {

    @Nullable
    Person findByLastname(@Nullable String lastname);

    Optional<Person> findByFirstname(String lastname);
}
