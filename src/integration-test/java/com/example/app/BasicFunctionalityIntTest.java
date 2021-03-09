package com.example.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.app.domain.Person;
import com.example.app.domain.populated.JsonPopulatedEntity;
import com.example.app.domain.populated.XmlPopulatedEntity;
import com.example.app.repository.PersonRepository;
import com.example.app.repository.populated.JsonPopulatedEntityRepository;
import com.example.app.repository.populated.XmlPopulatedEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasicFunctionalityIntTest extends AbstractIntTest {

    @Autowired
    JsonPopulatedEntityRepository jsonPopulatedEntityRepository;

    @Autowired
    XmlPopulatedEntityRepository xmlPopulatedEntityRepository;

    @Autowired
    PersonRepository personRepository;

    @Test
    void testJsonPopulation() {
        Optional<JsonPopulatedEntity> entityOptional = jsonPopulatedEntityRepository.findById(1L);

        assertTrue(entityOptional.isPresent());

        JsonPopulatedEntity entity = entityOptional.get();

        assertAll(
                () -> assertEquals("json-firstname", entity.getFirstname()),
                () -> assertEquals("json-lastname", entity.getLastname())
        );
    }

    @Test
    void testXmlPopulation() {
        Optional<XmlPopulatedEntity> entityOptional = xmlPopulatedEntityRepository.findById(1L);

        assertTrue(entityOptional.isPresent());

        XmlPopulatedEntity entity = entityOptional.get();

        assertAll(
                () -> assertEquals("xml-firstname", entity.getFirstname()),
                () -> assertEquals("xml-lastname", entity.getLastname())
        );
    }

    @Test
    @Sql(scripts = "classpath:sql/delete-by-lastname.sql")
    void shouldDeleteByLastname() {
        {
            List<Person> personList = transactionTemplate.execute(status -> toList(personRepository.findAll()));
            assertNotNull(personList);
            assertEquals(2, personList.size());
        }

        transactionTemplate.executeWithoutResult((s) -> personRepository.deleteByLastnameIgnoreCase("Brown"));

        {
            List<Person> personList = transactionTemplate.execute(status -> toList(personRepository.findAll()));
            assertNotNull(personList);
            assertEquals(0, personList.size());
        }
    }

    @Test
    @Sql(scripts = "classpath:sql/custom-query-data.sql")
    void shouldExecuteCustomQuery() {
        Optional<Person> personOptional = personRepository.findByFirstnameAndLastname("Ted", "Brown");

        assertTrue(personOptional.isPresent());

        Person entity = personOptional.get();

        assertAll(
                () -> assertEquals("Ted", entity.getFirstname()),
                () -> assertEquals("Brown", entity.getLastname())
        );
    }

    @Test
    @Sql(scripts = "classpath:sql/custom-query-data.sql")
    void shouldExecuteCustomQueryAsynchronously() {
        personRepository.findByFirstnameAndLastnameAsync("Ted", "Brown")
                .thenAccept(personOptional -> {
                    assertTrue(personOptional.isPresent());

                    Person entity = personOptional.get();

                    System.out.println(entity);

                    assertAll(
                            () -> assertEquals("Ted", entity.getFirstname()),
                            () -> assertEquals("Brown", entity.getLastname())
                    );
                });
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();

        iterable.forEach(list::add);
        return list;
    }
}
