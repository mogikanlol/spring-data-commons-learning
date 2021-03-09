package com.example.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.app.projection.ClassBased;
import com.example.app.projection.InterfaceBasedClosed;
import com.example.app.projection.InterfaceBasedClosedNullWrapper;
import com.example.app.projection.InterfaceBasedOpened;
import com.example.app.repository.projection.PersonProjectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

public class ProjectionIntTest extends AbstractIntTest {

    @Autowired
    private PersonProjectionRepository personProjectionRepository;

    @Test
    @Sql(scripts = "classpath:sql/projection-data.sql")
    void testInterfaceBasedClosedProjection() {
        InterfaceBasedClosed projection = personProjectionRepository.readById(1L);

        assertAll(
                () -> assertEquals("Jon", projection.getFirstname()),
                () -> assertEquals("Brown", projection.getLastname())
        );
    }

    @Test
    @Sql(scripts = "classpath:sql/projection-data.sql")
    void testInterfaceBasedClosedNullWrapperProjection() {
        InterfaceBasedClosedNullWrapper projection = personProjectionRepository.getById(2L);

        Optional<String> firstnameOptional = projection.getFirstname();

        assertTrue(firstnameOptional.isPresent());
        assertEquals("Ted", firstnameOptional.get());
    }

    @Test
    @Sql(scripts = "classpath:sql/projection-data.sql")
    void testInterfaceBasedOpenedProjection() {
        InterfaceBasedOpened projection = personProjectionRepository.queryById(3L);

        assertEquals("Josh Brown", projection.getFullName());
    }

    @Test
    @Sql(scripts = "classpath:sql/projection-data.sql")
    void testClassBasedProjection() {
        ClassBased projection = personProjectionRepository.searchById(4L);

        assertAll(
                () -> assertEquals("Rob", projection.getFirstname()),
                () -> assertEquals("Brown", projection.getLastname())
        );
    }

    @Test
    @Sql(scripts = "classpath:sql/projection-data.sql")
    void testDynamicProjection() {
        ClassBased projection = personProjectionRepository.findById(1L, ClassBased.class);

        assertAll(
                () -> assertEquals("Jon", projection.getFirstname()),
                () -> assertEquals("Brown", projection.getLastname())
        );
    }

}
