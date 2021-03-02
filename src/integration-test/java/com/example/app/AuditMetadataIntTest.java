package com.example.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.app.domain.AuditMetadata;
import com.example.app.domain.Customer;
import com.example.app.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

public class AuditMetadataIntTest extends AbstractIntTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testMetadataPopulation() {

        SecurityContext context = SecurityContextHolder.getContext();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                "test",
                "test123", List.of(new SimpleGrantedAuthority("ADMIN"))
        );

        context.setAuthentication(authentication);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setAuditMetadata(new AuditMetadata());

        customerRepository.save(customer);


        Optional<Customer> entityOptional = customerRepository.findById(1L);

        assertTrue(entityOptional.isPresent());

        Customer entity = entityOptional.get();

        assertAll(
                () -> assertEquals("test", entity.getAuditMetadata().getUsername()),
                () -> assertNotNull(entity.getAuditMetadata().getCreatedDate())
        );

    }

}
