package com.dev2qa.parkedup2;

import android.location.Location;

//public class LocationManager implements LocationI {
public class LocationManager {
    private float[] coordinates;
    private double distance;
    private float elevation;
    private float[] parkingCoord;
    private float parkingElev;

    public LocationManager() {
        coordinates = new float[]{0f,0f};
        //distance = distanceToCar(coordinates);
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

    private double degToRad(float deg) {
        return deg * Math.PI / 180;
    }
    public double distanceToCar() {
        int earthRadius = 3959; //mi

        double lat = degToRad(coordinates[0]-parkingCoord[0]);
        double lon = degToRad(coordinates[1]-parkingCoord[1]);
        double latCurrent = degToRad(coordinates[0]);
        double latParked = degToRad(parkingCoord[0]);

        //Haversine formula
        double a = Math.sin(lat/2) * Math.sin(lat/2) +
                Math.cos(latCurrent) * Math.cos(latParked) * Math.sin(lon/2) * Math.sin(lon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return c * earthRadius;
    }
    private String timeFormatted(double time) { //hours
        StringBuilder str = new StringBuilder();
        //Days
        if (time > 24) {
            time /= 24;
            str.append(String.format("%.0f days", Math.floor(time)));
        }
        //Hours
        if (time > 1) {
            time %= 1;
            time *= 24;
            if (str.length() > 0)
                str.append(", ");
            str.append(String.format("%.0f hrs",  Math.floor(time)));
        }
        //Minutes
        if (time > 1) {
            time %= 1;
            time *= 60;
            if (str.length() > 0)
                str.append(", ");
            str.append(String.format("%.0f mins",  Math.floor(time)));
        }
        //Seconds
        if (time > 1) {
            time %= 1;
            time *= 60;
            if (str.length() > 0)
                str.append(", ");
            str.append(String.format("%.0f secs",  Math.floor(time)));
        }

        System.out.println(str.toString());
        return str.toString();
    }
    public String timeToCar(double distance) { //in miles
        double time = distance/2; //2mph; average walking pace
        return timeFormatted(time);
    }

}
