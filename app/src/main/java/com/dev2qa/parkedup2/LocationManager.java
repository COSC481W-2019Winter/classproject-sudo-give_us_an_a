package com.dev2qa.parkedup2;

import android.location.Location;

//public class LocationManager implements LocationI {
public class LocationManager {
    private double[] coordinates;
    private double distance;
    private double elevation;
    private double[] parkingCoord;
    private double parkingElev;

    public LocationManager(){
        coordinates = new double[]{};
        distance = 0;
        elevation = 0;
        parkingCoord = null;
        parkingElev = 0;
    }

    public LocationManager(Location location) {
        coordinates = new double[]{location.getLatitude(),location.getLongitude()};
        distance = 0;
        elevation = 0;
        parkingCoord = null;
        parkingElev = 0;
    }

    public double getElevation() {
        return elevation;
    }

    public void setParkCoord(Location location) {
        //public void setParkCoord(double lat1, double lng1, double lat2, double lng2) { // for testing
        parkingCoord = new double[]{location.getLatitude(),location.getLongitude()};
        //coordinates = new double[]{lat2,lng2}; // for testing
    }

    public void setCurrCoord(Location location) {
        coordinates = new double[]{location.getLatitude(),location.getLongitude()};
    }
    private String displayFormatted(double[] coords) {
        //Default coordinate is positive
        String lat = "N";
        String lng = "E";

        StringBuilder str = new StringBuilder();
        if (coords[0] < 0) {
            lat = "S";
            coords[0] *= -1;
        }
        str.append(String.format("%.4f",coords[0]) + "°" + lat);
        str.append(", ");
        if (coords[1] < 0) {
            lng = "W";
            coords[1] *= -1;
        }
        str.append(String.format("%.4f",coords[1]) + "°" + lng);
        return str.toString();

    }
    public String displayCoord(){
        return displayFormatted(coordinates);
    }

    public String displayParkCoord(){
        return displayFormatted(parkingCoord);
    }

    public void setParkElev(Location location) {
        //Will set parkingElev
    }
    public double getDistance() {
        distanceToCar();
        return distance;
    }
    private double degToRad(double deg) {
        return deg * Math.PI / 180;
    }
    public void distanceToCar() {
        //public double distanceToCar() {
        int earthRadius = 3959; //mi

        double lat = degToRad(coordinates[0]-parkingCoord[0]);
        double lon = degToRad(coordinates[1]-parkingCoord[1]);
        double latCurrent = degToRad(coordinates[0]);
        double latParked = degToRad(parkingCoord[0]);

        //Haversine formula
        double a = Math.sin(lat/2) * Math.sin(lat/2) +
                Math.cos(latCurrent) * Math.cos(latParked) * Math.sin(lon/2) * Math.sin(lon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        //return c * earthRadius; //for testing
        distance = c * earthRadius;
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
            //Calculation rounding-error handling
            if ((1 - (time % 1)) < (1 / 24.0)) {
                time = Math.ceil(time);
                str.append(String.format("%.0f hrs",  time));
                time %= 1;
            }
            else
                str.append(String.format("%.0f hrs",  Math.floor(time)));
        }
        else
            time *= 60;
        //Minutes
        if (time > 1) {
            if ((time % 1 != 0) && (str.length() > 0)) {
                time %= 1;
                time *= 60;
            }
            if (str.length() > 0)
                str.append(", ");
            //Calculation rounding-error handling
            if ((1 - (time % 1)) < (1 / 60.0)) {
                time = Math.ceil(time);
                str.append(String.format("%.0f mins",  time));
                time %= 1;
            }
            else
                str.append(String.format("%.0f mins",  Math.floor(time)));
        }
        else
            time *= 60;
        //Seconds
        if (time > 1) {
            if (str.length() > 0) {
                time %= 1;
                time *= 60;
            }
            //Calculation rounding-error handling
            if ((1 - (time % 1)) < (1 / 60.0)) {
                time = Math.ceil(time);
                if (str.length() > 0)
                    str.append(", ");
                str.append(String.format("%.0f secs",  time));
                time %= 1;
            }
            else if (time > 0) {
                if (str.length() > 0)
                    str.append(", ");
                str.append(String.format("%.0f secs", Math.floor(time)));
            }
        }

        return str.toString();
    }
    //in miles
    public String timeToCar() {
        //public String timeToCar(double distance) {  // for testing
        double time = distance/2; //2mph; average walking pace
        return timeFormatted(time);
    }

}