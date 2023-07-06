//package com.example.googlevue11_server.controller;
//
//
//import com.example.googlevue11_server.events.*;
//import com.example.googlevue11_server.models.Location;
//import com.example.googlevue11_server.models.Trip;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/trips")
//public class TripController1 {
//
//
//    @PostMapping
//    public ResponseEntity<Trip> createTrip(@Validated @RequestBody Trip trip) {
//        // Validate request
//        if (trip.getOrigin() == null || trip.getDestination() == null || trip.getDestinationName() == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // Save trip
//        // Replace this with your own logic to create a trip
//        // Example: tripService.createTrip(trip);
//        // ...
//
//        // Dispatch TripCreated event
//        TripCreated event = new TripCreated(trip.getId(), trip.getDriverId()); // Replace with your own event class and implementation
//       // eventDispatcher.dispatch(event);
//
//        return ResponseEntity.ok(trip);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Trip> getTrip(@PathVariable("id") String id) {
//        // Fetch trip by ID
//        // Replace this with your own logic to fetch a trip by ID
//        // Example: Trip trip = tripService.getTripById(id);
//        Trip trip = null;
//
//        if (trip == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(trip);
//    }
//
//    @PostMapping("/{id}/accept")
//    public ResponseEntity<Trip> acceptTrip(@PathVariable("id") String id, @Validated @RequestBody Location location) {
//        // Fetch trip by ID
//        // Replace this with your own logic to fetch a trip by ID
//        // Example: Trip trip = tripService.getTripById(id);
//        Trip trip = null;
//
//        if (trip == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Validate request
//        if (location.getDriverLocation() == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // Update trip with driver information
//        trip.setDriverId(location.getDriverId()); // Replace with appropriate driver ID
//        trip.setDriverLocation(location.getDriverLocation());
//
//        // Dispatch TripAccepted event
//        TripAccepted event = new TripAccepted(location.getTripId(), location.getDriverId()); // Replace with your own event class and implementation
//        // eventDispatcher.dispatch(event);
//
//        return ResponseEntity.ok(trip);
//    }
//
//    @PostMapping("/{id}/start")
//    public ResponseEntity<Trip> startTrip(@PathVariable("id") String id) {
//        // Fetch trip by ID
//        // Replace this with your own logic to fetch a trip by ID
//        // Example: Trip trip = tripService.getTripById(id);
//        Trip trip = null;
//
//        if (trip == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Update trip as started
//        trip.setStarted(true);
//
//        // Dispatch TripStarted event
//        TripStarted event = new TripStarted(trip.getId(), trip.getDriverId()); // Replace with your own event class and implementation
//        // eventDispatcher.dispatch(event);
//
//        return ResponseEntity.ok(trip);
//    }
//
//    @PostMapping("/{id}/end")
//    public ResponseEntity<Trip> endTrip(@PathVariable("id") String id, Trip trip) {
//        // Fetch trip by ID
//        // Replace this with your own logic to fetch a trip by ID
//        // Example: Trip trip = tripService.getTripById(id);
//        //Trip trip = null;
//
//        if (trip == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Update trip as completed
//        trip.setComplete(true);
//
//        // Dispatch TripEnded event
//        TripEnded event = new TripEnded(trip.getId(), trip.getDriverId()); // Replace with your own event class and implementation
//        // eventDispatcher.dispatch(event);
//
//        return ResponseEntity.ok(trip);
//    }
//
//    @PostMapping("/{id}/location")
//    public ResponseEntity<Trip> updateLocation(@PathVariable("id") String id, @Validated @RequestBody Location location) {
//        // Fetch trip by ID
//        // Replace this with your own logic to fetch a trip by ID
//        // Example: Trip trip = tripService.getTripById(id);
//        Trip trip = null;
//
//        if (trip == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Validate request
//        if (location.getDriverLocation() == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // Update driver location in trip
//        trip.setDriverLocation(location.getDriverLocation());
//
//        // Dispatch TripLocationUpdated event
//        TripLocationUpdated event = new TripLocationUpdated(trip.getId(),location.getLatitude(), location.getLongitude()); // Replace with your own event class and implementation
//        // eventDispatcher.dispatch(event);
//
//        return ResponseEntity.ok(trip);
//    }
//}
