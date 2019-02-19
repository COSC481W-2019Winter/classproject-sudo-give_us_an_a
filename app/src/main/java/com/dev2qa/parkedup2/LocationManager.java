package com.dev2qa.parkedup2;

import com.google.android.gms.maps.GoogleMap;

public class LocationManager implements Location {
    private GoogleMap mMap;
    private float[] coordinates;
    private float distance;
    private float elevaton;

    public LocationManager() {
        float[] coordinates = {0.0f,0.0f};
        float distance = 0;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public float getElevation() {
        return elevaton;
    }

    public void setParkCoord(GoogleMap map) {

    }

    public void setParkElev(GoogleMap map) {

    }

    public float distanceToCar(float[] coordinates) {

    }

    public String timeToCar(float distance) {

    }

}
