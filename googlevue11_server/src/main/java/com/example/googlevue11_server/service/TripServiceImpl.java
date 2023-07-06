package com.example.googlevue11_server.service;

import com.example.googlevue11_server.events.TripEvent;
import com.example.googlevue11_server.events.EventDispatcher;
import com.example.googlevue11_server.exception.TripNotFoundException;
import com.example.googlevue11_server.models.Trip;
import com.example.googlevue11_server.models.User;
import com.example.googlevue11_server.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final EventDispatcher eventDispatcher;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, EventDispatcher eventDispatcher) {
        this.tripRepository = tripRepository;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public Trip createTrip(String origin, String destination, String destinationName) {
        // Create a new Trip object
        Trip trip = new Trip();
        trip.setOrigin(origin);
        trip.setDestination(destination);
        trip.setDestinationName(destinationName);

        // Set any other necessary properties

        // Save the trip to the database (assuming you have a tripRepository)
        tripRepository.save(trip);

        return trip;
    }


    @Override
    public Trip getTripById(Long tripId) {
        // Use the tripRepository to retrieve the trip by ID
        Optional<Trip> optionalTrip = tripRepository.findById(String.valueOf(tripId));

        // Check if the trip exists
        if (optionalTrip.isPresent()) {
            return optionalTrip.get();
        } else {
            // Handle the case when the trip is not found
            throw new TripNotFoundException("Trip not found with ID: " + tripId);
        }
    }

    @Override
    public void acceptTrip(Trip trip, User driver, String driverLocation) {
        // Implement the logic to accept a trip
    }

    @Override
    public void startTrip(Trip trip) {
        // Implement the logic to start a trip
    }

    @Override
    public void endTrip(Trip trip) {
        // Implement the logic to end a trip
    }

    @Override
    public void updateDriverLocation(Trip trip, String driverLocation) {
        // Implement the logic to update the driver's location
    }

    @Override
    public void dispatchEvent(TripEvent event) {
      //  eventDispatcher.dispatchEvent(event);
    }
}