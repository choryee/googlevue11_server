package com.example.googlevue11_server.events;

import com.example.googlevue11_server.events.TripEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public EventDispatcher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void dispatchEvent(TripEvent event) {
        eventPublisher.publishEvent(event);
    }
}
