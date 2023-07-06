package com.example.googlevue11_server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "is_started", nullable = true)
    private boolean started;

    @Column(name = "is_complete", nullable = true)
    private boolean complete;

    @Column(name = "origin", columnDefinition = "JSON")
    private String origin;

    @Column(name = "destination", columnDefinition = "JSON")
    private String destination;

    @Column(name = "destination_name")
    private String destinationName;

    @Column(name = "driver_location", columnDefinition = "JSON")
    private String driverLocation;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean belongsToUser(User user) {
        return getUser().equals(user);
    }

    public boolean canBeAcceptedByDriver(User user) {
        // Check if the trip is not already accepted by another driver
        if (getDriver() != null) {
            return false;
        }

        // Check if the user is a driver
//        if (!user.isDriver()) {
//            return false;
//        }

        // Additional checks specific to your business logic
        // ...

        return true;
    }

    public boolean canBeStartedByDriver(User user) {
        // Check if the trip has been accepted by the given driver
        if (getDriver() == null || !getDriver().equals(user)) {
            return false;
        }

        // Check if the trip has not been started yet
        if (isStarted()) {
            return false;
        }

        // Additional checks specific to your business logic
        // ...

        return true;
    }

    public boolean canBeEndedByDriver(User user) {
        // Check if the trip has been accepted by the given driver
        if (getDriver() == null || !getDriver().equals(user)) {
            return false;
        }

        // Check if the trip has been started
        if (!isStarted()) {
            return false;
        }

        // Check if the trip has not been completed yet
        if (isComplete()) {
            return false;
        }

        // Additional checks specific to your business logic
        // ...

        return true;
    }

    public boolean belongsToDriver(User user) {
        // Check if the trip has a driver associated with it
        if (getDriver() == null) {
            return false;
        }

        // Check if the associated driver matches the given user
        return getDriver().equals(user);
    }

}