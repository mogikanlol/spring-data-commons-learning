package com.example.app.projection;

import org.springframework.beans.factory.annotation.Value;

public interface InterfaceBasedOpened {

    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();
}
