package com.example.googlevue11_server.events;



public class TripEnded {
    private int tripId;
    private int userId;

    public TripEnded(int tripId, int userId) {
        this.tripId = tripId;
        this.userId = userId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
