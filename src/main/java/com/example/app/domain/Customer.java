package com.example.app.domain;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

}
