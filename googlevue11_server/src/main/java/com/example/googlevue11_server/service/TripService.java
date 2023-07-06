package com.example.googlevue11_server.service;
import com.example.googlevue11_server.events.TripEvent;
import com.example.googlevue11_server.models.Trip;
import com.example.googlevue11_server.models.User;

public interface TripService {
    Trip createTrip(String origin, String destination, String destinationName);

    Trip getTripById(Long tripId);

    void acceptTrip(Trip trip, User driver, String driverLocation);

    void startTrip(Trip trip);

    void endTrip(Trip trip);

    void updateDriverLocation(Trip trip, String driverLocation);

    void dispatchEvent(TripEvent event);
}
