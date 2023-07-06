package com.example.googlevue11_server.events;
import com.example.googlevue11_server.models.Trip;
import com.example.googlevue11_server.models.User;

public class TripEndedEvent extends TripEvent {
    public TripEndedEvent(Trip trip, User user) {
        super(trip, user);
    }
}