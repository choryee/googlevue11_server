package com.example.googlevue11_server.events;
import com.example.googlevue11_server.models.Trip;
import com.example.googlevue11_server.models.User;

public class TripLocationUpdatedEvent extends TripEvent {
    public TripLocationUpdatedEvent(Trip trip, User user) {
        super(trip, user);
    }
}