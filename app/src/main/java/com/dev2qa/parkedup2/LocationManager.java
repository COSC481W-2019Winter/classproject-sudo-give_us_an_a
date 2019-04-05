package com.dev2qa.parkedup2;

import android.location.Location;

public class LocationManager {
    private double[] coordinates;
    private double distance;
    private double elevation;
    private double[] parkingCoord;
    private double parkingElev;
    private boolean usUnits;
	private double speed;

    public LocationManager(){
        usUnits = MenuActivity.getMiles();
        coordinates = null;
        distance = 0;
        elevation = 0;
        parkingCoord = new double[]{};
        parkingElev = 0;
		speed = 0;
    }
    public double[] getCoordinates(){
        return coordinates;
    }
    public double[] getParkingCoord() {
        return parkingCoord;
    }
    public void setSpeed(Location location) {
        speed = (double) location.getSpeed();
    }

    public void setParkCoord(Location location) {
        parkingCoord = new double[]{location.getLatitude(),location.getLongitude()};
    }

    public void setCurrCoord(Location location) {
        coordinates = new double[]{location.getLatitude(),location.getLongitude()};
    }
    private String formattedCoords(double[] array) {
        double[] coords = new double[]{array[0],array[1]};
        //Default coordinate is positive
        String lat = "N";
        String lng = "E";
        StringBuilder str = new StringBuilder();

        //Latitude
        if (coords[0] < 0) {
            lat = "S";
            coords[0] *= -1;
        }
        double latMin = (coords[0] % 1) * 60.0;
        double latSec = (latMin % 1 ) * 60.0;
        str.append(String.format("%.0f",Math.floor(coords[0])));
        str.append("°");
        str.append(String.format("%.0f",Math.floor(latMin)));
        str.append("'");
        str.append(String.format("%.2f", latSec));
        str.append("\"" + lat);

        str.append(", ");

        //Longitude
        if (coords[1] < 0) {
            lng = "W";
            coords[1] *= -1;
        }
        double lngMin = (coords[1] % 1) * 60.0;
        double lngSec = (lngMin % 1 ) * 60.0;
        str.append(String.format("%.0f",Math.floor(coords[1])));
        str.append("°");
        str.append(String.format("%.0f",Math.floor(lngMin)));
        str.append("'");
        str.append(String.format("%.2f", lngSec));
        str.append("\"" + lng);

        return str.toString();

    }
    public String displayCoord(){
        return formattedCoords(coordinates);
    }

    public String displayParkCoord(){
        return formattedCoords(parkingCoord);
    }

    public double getElevation() { return elevation; }
    public double getParkElevation() { return parkingElev; }

    public void setParkElevation(float altitude) {
        if(usUnits)
            parkingElev = altitude * 0.000621371; //meters to miles;
        else
            parkingElev = altitude/1000;
    }

    public void setElevation(float altitude) {
        if(usUnits)
            elevation = altitude * 0.000621371; //meters to miles;
        else
            elevation = altitude/1000;
    }
    public String getDistance() {
        distanceToCar();

        double dist = distance;
        String units, smallUnit;
        double threshold;
        int conversionFactor;
        if (usUnits) {
            units = "miles";
            smallUnit = "feet";
            threshold = 0.19; //mi
            conversionFactor = 5280; //miles to feet
        }
        else {
            units = "kilometers";
            smallUnit = "meters";
            threshold = 1; //km
            conversionFactor = 1000; //km to m
        }
        if (dist < threshold) {
            dist *= conversionFactor;
            units = smallUnit;
        }
        
        return String.format("%.3f",dist) + " " + units;
    }
    private double degToRad(double deg) {
        return deg * Math.PI / 180;
    }
    private void distanceToCar() {
        int earthRadius;
        if (usUnits)
            earthRadius = 3959; //mi
        else
            earthRadius = 6371; //km

        double lat = degToRad(coordinates[0]-parkingCoord[0]);
        double lon = degToRad(coordinates[1]-parkingCoord[1]);
        double latCurrent = degToRad(coordinates[0]);
        double latParked = degToRad(parkingCoord[0]);

        //Haversine formula
        double a = Math.sin(lat/2) * Math.sin(lat/2) +
                Math.cos(latCurrent) * Math.cos(latParked) * Math.sin(lon/2) * Math.sin(lon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

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
                if (time == 1)
                    str.append(String.format("%.0f hr",  time));
                else
                    str.append(String.format("%.0f hrs",  time));
                time %= 1;
            }
            else {
                if (Math.floor(time) == 1)
                    str.append(String.format("%.0f hr", Math.floor(time)));
                else
                    str.append(String.format("%.0f hrs", Math.floor(time)));
            }
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
                if (time == 1)
                    str.append(String.format("%.0f min",  time));
                else
                    str.append(String.format("%.0f mins",  time));
                time %= 1;
            }
            else {
                if (Math.floor(time) == 1)
                    str.append(String.format("%.0f min",  Math.floor(time)));
                else
                    str.append(String.format("%.0f mins",  Math.floor(time)));
            }

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
                if (time == 1)
                    str.append(String.format("%.0f sec",  time));
                else
                    str.append(String.format("%.0f secs",  time));
                time %= 1;
            }
            else if (time > 0) {
                if (str.length() > 0)
                    str.append(", ");
                if (Math.floor(time) == 1)
                    str.append(String.format("%.0f sec",  Math.floor(time)));
                else
                    str.append(String.format("%.0f secs",  Math.floor(time)));
            }
        }

        if (str.length() < 1)
            return "0 secs";
        else
            return str.toString();
    }

    public String timeToCar() {
        double convertedSpeed;
        double averageWalkingPace;
        if (usUnits) {
            convertedSpeed = speed * 2.237; //m/s to mph
            averageWalkingPace = 2; //mph
        }
        else {
            convertedSpeed = speed * 3.6; //m/s to km/h
            averageWalkingPace = 3.219; //km/h
        }

        double time;
        //if walking slower than half averageWalkingPace, assume stationary
        if (convertedSpeed < averageWalkingPace/2)
            time = distance/averageWalkingPace;
        else
            time = distance/convertedSpeed;

        return timeFormatted(time);
    }

}
