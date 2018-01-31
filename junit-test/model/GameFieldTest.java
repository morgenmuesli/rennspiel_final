package model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameFieldTest {
    GameField testField = new GameField(100, 100);

    @Test
    void correctCarX() {
        Car testCar = new Car(0, 0, 10, 10, 0, 10, 150, 0);
        Point2D start = new Point2D(5, 5);
        testCar.savePosition();
        testCar.setSpeed(100);
        testCar.updatePosition(10);
        testField.correctCar(testCar);
        assertEquals(new Point2D(5, 5), testCar.center());

    }

    @Test
    void correctCarY() {
        Car testCar = new Car(0, 0, 10, 10, 0, 10, 150, 0);
        Point2D start = new Point2D(5, 5);
        testCar.savePosition();
        testCar.setSpeed(100);
        testCar.steerCar(1);
        testCar.updatePosition(10);
        testField.correctCar(testCar);
        assertEquals(new Point2D(5, 5), testCar.center());

    }


}