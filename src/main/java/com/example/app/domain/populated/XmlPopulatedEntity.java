package com.example.app.domain.populated;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
@Entity
public class XmlPopulatedEntity {

    @Id
    private Long id;

    private String firstname;

    private String lastname;

}
