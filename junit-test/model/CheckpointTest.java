package model;

import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CheckpointTest {
    Checkpoint checkpoint = new Checkpoint(10, 10, 10, 0, false);

    @Test
    void move() {

        checkpoint.move(20, 20);
        assertEquals(new Point2D(35, 35), checkpoint.center());
    }

    @Test
    void getIsReached() {
        Car testCar = new Car(10, 10, 10, 60, 0, 0, 0, 0);
        assertTrue(checkpoint.getIsReached(testCar));


    }
}