package com.example.googlevue11_server.events;

import com.example.googlevue11_server.models.Trip;
import com.example.googlevue11_server.models.User;
import org.springframework.context.ApplicationEvent;

public class TripEvent extends ApplicationEvent {
    private final Trip trip;
    private final User user;

    public TripEvent(Trip trip, User user) {
        super(trip);
        this.trip = trip;
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public User getUser() {
        return user;
    }

     class TripCreatedEvent extends TripEvent {
        public TripCreatedEvent(Trip trip, User user) {
            super(trip, user);
        }
    }

     class TripAcceptedEvent extends TripEvent {
        public TripAcceptedEvent(Trip trip, User user) {
            super(trip, user);
        }
    }

     class TripStartedEvent extends TripEvent {
        public TripStartedEvent(Trip trip, User user) {
            super(trip, user);
        }
    }

     class TripEndedEvent extends TripEvent {
        public TripEndedEvent(Trip trip, User user) {
            super(trip, user);
        }
    }

     class TripLocationUpdatedEvent extends TripEvent {
        public TripLocationUpdatedEvent(Trip trip, User user) {
            super(trip, user);
        }
    }
}
