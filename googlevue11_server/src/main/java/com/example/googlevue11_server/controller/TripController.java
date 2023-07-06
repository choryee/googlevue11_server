package com.example.googlevue11_server.controller;

import com.example.googlevue11_server.events.TripAcceptedEvent;
import com.example.googlevue11_server.events.TripCreatedEvent;
import com.example.googlevue11_server.events.TripEndedEvent;
import com.example.googlevue11_server.events.TripLocationUpdatedEvent;
import com.example.googlevue11_server.events.TripStartedEvent;
import com.example.googlevue11_server.models.Trip;
import com.example.googlevue11_server.models.User;
//import com.example.googlevue11_server.request.CurrentUser;
import com.example.googlevue11_server.request.DriverLocationRequest;
import com.example.googlevue11_server.request.TripRequest;
import com.example.googlevue11_server.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<Trip> store(@Valid @RequestBody TripRequest request, User user) {
        Trip trip = tripService.createTrip(request.getOrigin(), request.getDestination(), request.getDestinationName());
        TripCreatedEvent event = new TripCreatedEvent(trip, user);
        tripService.dispatchEvent(event);
        return ResponseEntity.ok(trip);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> show(@PathVariable Long tripId,  User user) {
        Trip trip = tripService.getTripById(tripId);
        if (trip == null || !trip.belongsToUser(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(trip);
    }

    @PutMapping("/{tripId}/accept")
    public ResponseEntity<Trip> accept(@PathVariable Long tripId, @Valid @RequestBody DriverLocationRequest request, User user) {
        Trip trip = tripService.getTripById(tripId);
        if (trip == null || !trip.canBeAcceptedByDriver(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        tripService.acceptTrip(trip, user, request.getDriverLocation());
        TripAcceptedEvent event = new TripAcceptedEvent(trip, user);
        tripService.dispatchEvent(event);
        return ResponseEntity.ok(trip);
    }

    @PutMapping("/{tripId}/start")
    public ResponseEntity<Trip> start(@PathVariable Long tripId,  User user) {
        Trip trip = tripService.getTripById(tripId);
        if (trip == null || !trip.canBeStartedByDriver(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        tripService.startTrip(trip);
        TripStartedEvent event = new TripStartedEvent(trip, user);
        tripService.dispatchEvent(event);
        return ResponseEntity.ok(trip);
    }

    @PutMapping("/{tripId}/end")
    public ResponseEntity<Trip> end(@PathVariable Long tripId,  User user) {
        Trip trip = tripService.getTripById(tripId);
        if (trip == null || !trip.canBeEndedByDriver(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        tripService.endTrip(trip);
        TripEndedEvent event = new TripEndedEvent(trip, user);
        tripService.dispatchEvent(event);
        return ResponseEntity.ok(trip);
    }

    @PutMapping("/{tripId}/location")
    public ResponseEntity<Trip> updateLocation(@PathVariable Long tripId, @Valid @RequestBody DriverLocationRequest request,  User user) {
        Trip trip = tripService.getTripById(tripId);
        if (trip == null || !trip.belongsToDriver(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        tripService.updateDriverLocation(trip, request.getDriverLocation());
        TripLocationUpdatedEvent event = new TripLocationUpdatedEvent(trip, user);
        tripService.dispatchEvent(event);
        return ResponseEntity.ok(trip);
    }
}
