package com.dev2qa.parkedup2;

import android.location.Location;

public class LocationManager implements LocationI {
    private float[] coordinates;
    private float distance;
    private float elevation;
    private float[] parkingCoord;
    private float parkingElev;

    public LocationManager() {
        coordinates = new float[]{0f,0f};
        distance = 0;
        elevation = 0;
        parkingCoord = new float[]{0f,0f};
        parkingElev = 0;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public float getElevation() {
        return elevation;
    }

    public void setParkCoord(Location location) {
        //Will set parkingCoord
    }

    public void setParkElev(Location location) {
        //Will set parkingElev
    }

    public float distanceToCar(float[] coordinates) {
        //Will use distance formula
        return distance;
    }

    public String timeToCar(float distance) {
        float time = distance/2; //miles per hour
        return String.valueOf(time);
    }

}
