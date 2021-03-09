package com.example.app.configuration.populator;

import com.example.app.domain.populated.XmlPopulatedEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.UnmarshallerRepositoryPopulatorFactoryBean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class XmlDataPopulatorConfiguration {

    @Bean
    public UnmarshallerRepositoryPopulatorFactoryBean unmarshallerRepositoryPopulatorFactoryBean() {

        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(XmlPopulatedEntity.class);

        UnmarshallerRepositoryPopulatorFactoryBean bean = new UnmarshallerRepositoryPopulatorFactoryBean();
        bean.setUnmarshaller(jaxb2Marshaller);
        bean.setResources(new Resource[] {new ClassPathResource("population/data.xml")});
        return bean;
    }

}
