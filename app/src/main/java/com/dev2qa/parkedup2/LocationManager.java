package com.dev2qa.parkedup2;

import com.google.android.gms.maps.GoogleMap;

public class LocationManager implements Location {
    private GoogleMap mMap;
    private float[] coordinates;
    private float distance;
    private float elevation;
    private float[] parkingCoord;
    private float parkingElev;

    public LocationManager(Location location) {
        coordinates = location.getCoordinates();
        distance = 0;
        elevation = 0;
        parkingCoord = null;
        parkingElev = 0.0f;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public float getElevation() {
        return elevation;
    }

    public void setParkCoord(GoogleMap map) {
        //Will set parkingCoord
    }

    public void setParkElev(GoogleMap map) {
        //Will set parkingElev
    }

    public float distanceToCar(float[] coordinates) {
        return distance;
    }

    public String timeToCar(float distance) {
        float time = distance/ //miles per hour
        return String.valueOf(time);
    }

}
