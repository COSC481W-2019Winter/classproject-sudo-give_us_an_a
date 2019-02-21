package com.dev2qa.parkedup2;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationManagerTest {
    LocationManager obj = new LocationManager();
    @Test
    public void initialization() {
        assertTrue(obj instanceof LocationManager);
        assertNotNull(obj);
    }
    @Test
    public void getCoordinates() {
        assertTrue(obj.getCoordinates() instanceof float[]);
        assertNotNull(obj.getCoordinates());
    }

    @Test
    public void distanceToCar() {
        assertEquals(0f, obj.distanceToCar(obj.getCoordinates()), 0.0002);
        assertNotNull(obj.distanceToCar(obj.getCoordinates()));
    }

    @Test
    public void timeToCar() {
        assertTrue(obj.timeToCar(obj.distanceToCar(obj.getCoordinates())) instanceof String);
        assertNotNull(obj.timeToCar(obj.distanceToCar(obj.getCoordinates())));
    }
}