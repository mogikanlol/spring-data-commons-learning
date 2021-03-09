package com.example.app.repository.customized;

import com.example.app.domain.Person;

public class CustomizedPersonRepositoryImpl implements CustomizedPersonRepository {

    @Override
    public Person getCustomizedStub() {
        return new Person(42L, "customized-firstname", "customized-lastname");
    }
}
