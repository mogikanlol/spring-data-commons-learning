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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleIntTests extends AbstractIntTest {

    @Autowired
    JsonPopulatedEntityRepository jsonPopulatedEntityRepository;

    @Autowired
    XmlPopulatedEntityRepository xmlPopulatedEntityRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    TransactionTemplate transactionTemplate;

    @BeforeEach
    void setUp() {
        transactionTemplate = new TransactionTemplate(platformTransactionManager);
    }
    @Test
    void test_json_population() {
        Optional<JsonPopulatedEntity> entityOptional = jsonPopulatedEntityRepository.findById(1L);

        assertTrue(entityOptional.isPresent());

        JsonPopulatedEntity entity = entityOptional.get();

        assertAll(
                () -> assertEquals("json-firstname", entity.getFirstname()),
                () -> assertEquals("json-lastname", entity.getLastname())
        );
    }

    @Test
    void test_xml_population() {
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
    void test_delete_by_lastname() {
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


    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();

        iterable.forEach(list::add);
        return list;
    }
}
