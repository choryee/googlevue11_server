package com.example.googlevue11_server.events;



public class TripAccepted {
    private int tripId;
    private int driverId;

    public TripAccepted(int tripId, int driverId) {
        this.tripId = tripId;
        this.driverId = driverId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
}
