package com.example.app.repository;

import com.example.app.domain.Person;
import com.example.app.projection.ClassBased;
import com.example.app.projection.InterfaceBasedClosed;
import com.example.app.projection.InterfaceBasedClosedNullWrapper;
import com.example.app.projection.InterfaceBasedOpened;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonProjectionRepository extends CrudRepository<Person, Long>{

    InterfaceBasedClosed readById(Long id);

    InterfaceBasedClosedNullWrapper getById(Long id);

    InterfaceBasedOpened queryById(Long id);

    ClassBased searchById(Long id);

    <T> T findById(Long id, Class<T> type);
}
