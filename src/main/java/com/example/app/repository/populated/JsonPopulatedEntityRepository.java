package com.example.app.repository.populated;

import com.example.app.domain.populated.JsonPopulatedEntity;
import org.springframework.data.repository.CrudRepository;

public interface JsonPopulatedEntityRepository extends CrudRepository<JsonPopulatedEntity, Long> {
}
