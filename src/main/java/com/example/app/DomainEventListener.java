package com.example.app;

import com.example.app.domain.DomainEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DomainEventListener {

    @EventListener
    public void handleDomainEvent(DomainEvent domainEvent) {

        System.out.println("Handle domain event");
        System.out.println(domainEvent);

    }

}
