package com.dev2qa.parkedup2;

import android.location.Location;

public interface LocationI {

    /**
     * Returns a array of coordinates in latitude in longitude.
     * Must have already called for a set of coordinates to be
     * generated from a GoogleMap object, otherwise null pointer
     * will occur.
     *
     * @return the current coordinates of the android device's location
     */
    double[] getCoordinates();

    /**
     * Returns the current elevation of the android device.
     * Must have already called for a set of coordinates to be
     * generated from a GoogleMap object, otherwise null pointer
     * will occur.
     *
     * @return current elevation of device's location
     */
    double getElevation();

    /**
     * Stores the parking coordinates gathered from the GoogleMap
     * object into a double point array.
     *
     * @param mMap a GoogleMap object giving data for coordinates
     */
    void setParkCoord(Location location);

    /**
     * Stores the parking elevation gathered from the GoogleMap
     * object into a double point array.
     *
     * @param mMap a GoogleMap object giving data for coordinates
     */
    void setParkElev(Location location);

    /**
     * Returns the distance between the android device and the
     * saved parking location.
     *
     * @param coordinates a double array containing latitude and longitude
     * @return the distance to the user's car from their position.
     */
    double distanceToCar(double[] coordinates);

    /**
     * Returns a string used to print the time it will take the
     * user to travel to their parked car.
     *
     * @param distance the distance between the android device and the parked car.
     * @return the time to travel to the user's car from their position.
     */
    String timeToCar(double distance);


}