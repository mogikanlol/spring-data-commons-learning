package com.example.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.app.domain.Person;
import com.example.app.repository.PersonQueryByExampleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

public class QueryByExampleIntTest extends AbstractIntTest {

    @Autowired
    private PersonQueryByExampleRepository repository;

    @Test
    @Sql(scripts = "classpath:sql/query-by-example-data.sql")
    void testQueryByExample() {
        Person person = new Person();
        person.setFirstname("Jon");
        Example<Person> example = Example.of(person);

        Optional<Person> personOptional = repository.findOne(example);

        assertTrue(personOptional.isPresent());
        Person actualPerson = personOptional.get();
        assertAll(
                () -> assertEquals("Jon", actualPerson.getFirstname()),
                () -> assertEquals("Brown", actualPerson.getLastname()),
                () -> assertEquals(1L, actualPerson.getId())
        );

    }
}
