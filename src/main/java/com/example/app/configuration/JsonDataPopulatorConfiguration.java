package com.example.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class JsonDataPopulatorConfiguration {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean jackson2RepositoryPopulatorFactoryBean() {
        Jackson2RepositoryPopulatorFactoryBean bean = new Jackson2RepositoryPopulatorFactoryBean();
        bean.setResources(new Resource[] {new ClassPathResource("population/data.json")});
        return bean;
    }

}
