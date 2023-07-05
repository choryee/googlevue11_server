package com.example.googlevue11_server.events;



public class TripLocationUpdated {
    private int tripId;
    private double latitude;
    private double longitude;

    public TripLocationUpdated(int tripId, double latitude, double longitude) {
        this.tripId = tripId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}