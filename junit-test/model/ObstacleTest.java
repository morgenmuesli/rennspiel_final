package model;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleTest {
    JFXPanel jfxPanel = new JFXPanel();
    Obstacle testObstacle = new Obstacle(new Point2D(50, 50), 50, 50, 100);
    Car testCar = new Car(50, 50, 20, 20, 0, 0, 0, 0);

    @Test
    void isColliding() {


        assertTrue(testObstacle.isColliding(testCar));
    }

    @Test
    void isNotColliding() {
        testCar = new Car(0, 0, 10, 10, 0, 0, 0, 0);
        assertFalse(testObstacle.isColliding(testCar));
    }

    @Test
    void isCollidingAfterRotate() {
        testCar = new Car(38, 38, 10, 100, 0, 0, 0, 0);
        assertFalse(testObstacle.isColliding(testCar));
        testCar.steerCar(1);
        assertTrue(testObstacle.isColliding(testCar));
    }


}