package com.example.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.app.domain.Person;
import com.example.app.repository.PersonRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

public class PaginationIntTest extends AbstractIntTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Sql(scripts = "classpath:sql/pagination-data.sql")
    void testFirstPage() {
        Page<Person> zeroPage = transactionTemplate.execute(s -> personRepository.findAll(PageRequest.of(0, 4, Sort.by("id").ascending())));
        List<Person> actualPersons = zeroPage.getContent();

        assertAll(
                () -> assertEquals(5, zeroPage.getTotalElements()),
                () -> assertEquals(2, zeroPage.getTotalPages()),
                () -> assertEquals(4, zeroPage.getSize()),
                () -> assertTrue(zeroPage.hasContent())
        );

        List<Person> expectedPersons = List.of(
                new Person(1L, "Jon", "Brown"),
                new Person(3L, "Josh", "Brown"),
                new Person(2L, "Ted", "Brown"),
                new Person(4L, "Rob", "Brown")
        );

        assertThat(actualPersons, Matchers.containsInAnyOrder(expectedPersons.toArray()));
    }

    @Test
    @Sql(scripts = "classpath:sql/pagination-data.sql")
    void testSecondPage() {
        Page<Person> firstPage = transactionTemplate.execute(s -> personRepository.findAll(PageRequest.of(1, 4, Sort.by("id").ascending())));
        List<Person> actualPersons = firstPage.getContent();

        assertAll(
                () -> assertEquals(5, firstPage.getTotalElements()),
                () -> assertEquals(2, firstPage.getTotalPages()),
                () -> assertEquals(4, firstPage.getSize()),
                () -> assertTrue(firstPage.hasContent()),
                () -> assertEquals(1, actualPersons.size())
        );


        Person expectedPerson = new Person(5L, "Ryan", "Brown");
        assertThat(actualPersons, Matchers.containsInAnyOrder(expectedPerson));
    }

}
