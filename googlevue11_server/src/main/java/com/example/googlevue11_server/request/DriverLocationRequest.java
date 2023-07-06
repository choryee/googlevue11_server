package com.example.googlevue11_server.request;

import javax.validation.constraints.NotBlank;

public class DriverLocationRequest {
    @NotBlank(message = "Driver location is required")
    private String driverLocation;

    // Getters and setters

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }
}
