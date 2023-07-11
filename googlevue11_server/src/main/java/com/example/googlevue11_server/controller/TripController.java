package com.example.googlevue11_server.controller;

import com.example.googlevue11_server.events.*;
//import com.example.googlevue11_server.events.TripStartedEvent;
import com.example.googlevue11_server.models.Trip;
import com.example.googlevue11_server.models.User;
//import com.example.googlevue11_server.request.CurrentUser;
import com.example.googlevue11_server.request.DriverLocationRequest;
import com.example.googlevue11_server.request.TripRequest;
import com.example.googlevue11_server.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/trip")
    public ResponseEntity<Trip> save( @RequestBody TripRequest request, User user) {
        Trip trip = tripService.createTrip(request.getOrigin(), request.getDestination(), request.getDestinationName());
        System.out.println("trip>>>"+trip);
        TripEvent.TripCreatedEvent event = new TripEvent.TripCreatedEvent(trip, user);
        tripService.dispatchEvent(event);
        return ResponseEntity.ok(trip);
    }

    @GetMapping("/trip")
    public String test(){
        return "컨트롤러 탐!!";
    }


    @MessageMapping("/TripCreated")
   // public void handleTripCreatedEvent(@Payload TripCreatedEvent event) {
    public ResponseEntity<User> showDriver(RequestEntity<Void> requestEntity) {
        // TripCreated 이벤트를 처리하는 로직을 구현합니다.
        // 업데이트할 애플리케이션 로직을 추가하고 필요한 작업을 수행합니다.
        // event 객체에서 필요한 정보를 추출하여 사용할 수 있습니다.

        // 예시: 콘솔에 이벤트 정보를 출력합니다.
        System.out.println("TripCreated Event: " + event);

        // 추가적인 로직을 구현하세요.
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
        TripEvent.TripAcceptedEvent event = new TripEvent.TripAcceptedEvent(trip, user);
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
        TripEvent.TripStartedEvent event = new TripEvent.TripStartedEvent(trip, user);
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
        //tripService.dispatchEvent(event);
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
        //tripService.dispatchEvent(event);
        return ResponseEntity.ok(trip);
    }
}
