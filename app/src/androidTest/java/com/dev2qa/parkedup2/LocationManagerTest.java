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
    @Test
    public void distanceToCar() {
        // EMU REC center -83.625312 42.250265 // -83.622595 42.249729 Pray Harold
        obj.setParkCoord(42.250265,-83.625312,42.24972,-83.622595);
        assertEquals(0.14,obj.distanceToCar() , .005);
        // Pray Harold to Chicago -87.624207 41.875569
        obj.setParkCoord(41.875569,-87.624207,42.24972,-83.622595);
        assertEquals(206.88,obj.distanceToCar() , .005);

        //Week 1 tests
        //assertEquals(0f, obj.distanceToCar(obj.getCoordinates()), 0.0002);
        //assertNotNull(obj.distanceToCar(obj.getCoordinates()));
    }
//
//    @Test
//    public void timeToCar() {
//        assertTrue(obj.timeToCar(obj.distanceToCar(obj.getCoordinates())) instanceof String);
//        assertNotNull(obj.timeToCar(obj.distanceToCar(obj.getCoordinates())));
//    }

    @Test
    public void timeToCar() {
        assertEquals("4 days, 5 hrs, 7 mins, 30 secs",obj.timeToCar(202.25)); //pass
        assertEquals("4 days, 5 hrs, 6 mins",obj.timeToCar(202.2)); //pass
        assertEquals("4 days, 5 hrs",obj.timeToCar(202)); //pass
        assertEquals("3 mins, 36 secs",obj.timeToCar(0.12));
        assertEquals("36 mins",obj.timeToCar(1.2)); //pass
        assertEquals("36 secs",obj.timeToCar(0.02)); //pass
    }
}