package com.example.app.configuration;

import com.example.app.repository.base.MyRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
        basePackages = "com.example.app.repository",
        queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND,
        repositoryBaseClass = MyRepositoryImpl.class
)
public class JpaConfiguration {
}
