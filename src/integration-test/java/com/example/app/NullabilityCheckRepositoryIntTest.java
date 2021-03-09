package com.example.app;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.app.domain.Person;
import com.example.app.repository.nullability.NullabilityCheckRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NullabilityCheckRepositoryIntTest extends AbstractIntTest {

    @Autowired
    private NullabilityCheckRepository repository;

    @Test
    void shouldThrowIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.findByFirstname(null)
        );
    }

    @Test
    void shouldReturnNull() {
        Person person = repository.findByLastname(null);

        assertNull(person);
    }
}
