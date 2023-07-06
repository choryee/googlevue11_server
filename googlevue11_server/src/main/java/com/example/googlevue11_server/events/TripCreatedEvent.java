package com.example.googlevue11_server.events;
import com.example.googlevue11_server.models.Trip;
import com.example.googlevue11_server.models.User;

public class TripCreatedEvent extends TripEvent {
    public TripCreatedEvent(Trip trip, User user) {
        super(trip, user);
    }
}