package com.example.app.domain.populated;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class JsonPopulatedEntity {

    @Id
    private Long id;

    private String firstname;

    private String lastname;

}
