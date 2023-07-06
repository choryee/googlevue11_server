//package com.example.googlevue11_server.service;
//
//import com.example.googlevue11_server.events.EventDispatcher1;
//import com.example.googlevue11_server.events.TripLocationUpdated;
//import com.example.googlevue11_server.models.Trip;
//import com.example.googlevue11_server.repository.TripRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TripService1 {
//
//    private final TripRepository tripRepository;
//    private final EventDispatcher1 eventDispatcher;
//
//    @Autowired
//    public TripService1(TripRepository tripRepository, EventDispatcher1 eventDispatcher) {
//        this.tripRepository = tripRepository;
//        this.eventDispatcher = eventDispatcher;
//    }
//
//    public Trip getTripById(String id) {
//        // Fetch trip by ID from the repository
//        return tripRepository.findById(id).orElse(null);
//    }
//
//    public Trip updateTripLocation(int tripId, double latitude, double longitude) {
//        Trip trip = getTripById(String.valueOf(tripId));
//
//        if (trip == null) {
//            return null;
//        }
//
//        // Update trip location
////        trip.setLatitude(latitude);
////        trip.setLongitude(longitude);
//
//        // Save the updated trip to the repository
//        tripRepository.save(trip);
//
//        // Dispatch TripLocationUpdated event
//        TripLocationUpdated event = new TripLocationUpdated(tripId, latitude, longitude);
//        //eventDispatcher.dispatch(event);
//        eventDispatcher.handleTripLocationUpdatedEvent(event);
//
//        return trip;
//    }
//}
