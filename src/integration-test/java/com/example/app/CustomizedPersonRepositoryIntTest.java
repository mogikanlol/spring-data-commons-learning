package com.example.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.app.domain.Person;
import com.example.app.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomizedPersonRepositoryIntTest extends AbstractIntTest {

    @Autowired
    private PersonRepository repository;

    @Test
    void shouldReturnCustomizedPerson() {
        Person customizedStub = repository.getCustomizedStub();

        assertAll(
                () -> assertEquals(42L, customizedStub.getId()),
                () -> assertEquals("customized-firstname", customizedStub.getFirstname()),
                () -> assertEquals("customized-lastname", customizedStub.getLastname())
        );
    }

}
