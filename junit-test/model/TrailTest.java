package model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrailTest {

    Trail testTrail = new Trail(100, 100, 20, new Point2D(0, 0));

    @Test
    void getCenter() {
        Point2D expected = new Point2D(50, 50);
        assertEquals(expected, testTrail.getCenter());
    }

    @Test
    void getStartPosition() {
        Point2D expected = new Point2D(40, 100);
        assertEquals(expected, testTrail.getStartPosition());
    }

    @Test
    void isOnTrail() {
        Car car = new Car(50, 0, 10, 10, 0, 0, 0, 0);
        assertTrue(testTrail.isOnTrail(car.center()));


    }

    @Test
    void isNotOnTrail() {
        Car car = new Car(50, 50, 10, 10, 0, 0, 0, 0);
        assertFalse(testTrail.isOnTrail(car.center()));
    }
}