package com.example.app.domain;

import lombok.Data;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Collection;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    private Long id;

    @Embedded
    private AuditMetadata auditMetadata;


    @DomainEvents
    public Collection<DomainEvent> domainEvents() {
        return List.of(
                new DomainEvent(1L),
                new DomainEvent(2L),
                new DomainEvent(3L)
        );
    }

    @AfterDomainEventPublication
    void afterDomainEventPublication() {
        System.out.println("AfterDomainEventPublication is called");
    }
}
