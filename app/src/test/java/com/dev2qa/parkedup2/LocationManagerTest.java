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
//    @Test
//    public void getCoordinates() {
//        assertTrue(obj.getCoordinates() instanceof float[]);
//        assertNotNull(obj.getCoordinates());
//    }
//
//    @Test
//    public void distanceToCar() {
//        assertEquals(0f, obj.distanceToCar(obj.getCoordinates()), 0.0002);
//        assertNotNull(obj.distanceToCar(obj.getCoordinates()));
//    }
//
//    @Test
//    public void timeToCar() {
//        assertTrue(obj.timeToCar(obj.distanceToCar(obj.getCoordinates())) instanceof String);
//        assertNotNull(obj.timeToCar(obj.distanceToCar(obj.getCoordinates())));
//    }

    @Test
    public void timeToCar1() {
        assertEquals(obj.timeToCar(202.25),"4 days, 5 hrs, 7 mins, 30 secs"); //pass
        assertEquals(obj.timeToCar(202.2),"4 days, 5 hrs, 6 mins"); //pass
        assertEquals(obj.timeToCar(202),"4 days, 5 hrs"); //pass
        assertEquals(obj.timeToCar(1.2),"36 mins"); //pass
        //assertEquals(obj.timeToCar(0.12),"3 mins, 36 secs");
        //assertEquals(obj.timeToCar(0.02),"36 secs");
    }
}