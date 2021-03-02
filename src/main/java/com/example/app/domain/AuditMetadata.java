package com.example.app.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
public class AuditMetadata {

    @CreatedBy
    private String username;

    @CreatedDate
    private Instant createdDate;

}
