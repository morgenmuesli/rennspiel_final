package model;

import GameEnum.Subsoil;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;


import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    JFXPanel jfxPanel = new JFXPanel();
    Car testCar = new Car(0, 0, 40, 20, 0, 2, 140, 50);

    @BeforeTest
    void testCarSettings() {

        assertEquals(0, testCar.getCurrentSpeed());
        assertEquals(0, testCar.getY());
        assertEquals(0, testCar.getY());
        assertEquals(0, testCar.getAngle());
        assertEquals(new Point2D(20, 10), testCar.center());

    }

    @Test
    void accelerate2seconds() {
        testCar.accelerate(2, true);
        assertEquals(4, testCar.getVirtualSpeed());
    }

    @Test
    void calcSpeedOnGrasTest() {
        testCar.setVirtualSpeed(100);
        testCar.setSubsoil(Subsoil.GRAS);
        testCar.updatePosition(2);
        assertEquals(73, testCar.getCurrentSpeed(), 1);

    }

    @Test
    void calcSpeedOnTrailTest() {
        testCar.setVirtualSpeed(100);
        testCar.setSubsoil(Subsoil.PAVEMENT);
        testCar.updatePosition(2);
        assertEquals(92, testCar.getCurrentSpeed(), 1);

    }

    @Test
    void breakCar() {
        testCar.setSpeed(100);
        testCar.breakCar(1);
        assertEquals(10, testCar.getVirtualSpeed());


    }

    @Test
    void setCarLocation() {

        testCar.setCarLocation(10, 10);
        Point2D carPosition = testCar.center();
        assertEquals(new Point2D(30, 20), carPosition);


    }

    @Test
    void updatePositionX() {
        testCar.setSpeed(100);
        testCar.updatePosition(2);
        testCar.setSubsoil(Subsoil.PAVEMENT);
        Point2D expected = new Point2D(20, 10).add(146, 0);
        assertEquals(expected.getX(), testCar.center().getX(), 0.5);
        assertEquals(expected.getY(), testCar.center().getY(), 0.5);
    }

    @Test
    void updatePositionY() {
        testCar.setSpeed(100);
        testCar.steerCar(1);
        testCar.updatePosition(2);
        testCar.setSubsoil(Subsoil.PAVEMENT);
        Point2D expected = new Point2D(20, 10).add(0, 146);
        assertEquals(expected.getX(), testCar.center().getX(), 0.5);
        assertEquals(expected.getY(), testCar.center().getY(), 0.5);
    }

    @Test
    void setCarToSavedPosition() {
        testCar.savePosition();
        testCar.move(100, 100);
        assertEquals(new Point2D(120, 110), testCar.center());
        testCar.setCarToSavedPosition();
        assertEquals(new Point2D(20, 10), testCar.center());

    }

    @Test
    void resetCarAngle() {
        testCar.setAngle(36);
        testCar.resetCarAngle();
        assertEquals(0, testCar.getAngle());
    }

    @Test
    void steerCar() {
        testCar.steerCar(1);
        assertEquals(90, testCar.getAngle());

    }
}