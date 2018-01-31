package model;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {
    JFXPanel jfxPanel = new JFXPanel();
    GameModel model = new GameModel();
    ArrayList<Obstacle> testObstacles;

    @BeforeTest
    void createTestObstacles() {
        testObstacles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testObstacles.add(new Obstacle(new Point2D(400, 150), 10, 10, 100));
        }
    }

    @Test
    void newObstacles() {
        createTestObstacles();
        model.setTestObstacles(testObstacles);
        model.newObstacles();
        assertNotEquals(model.getObstacles(), testObstacles);


    }

    @Test
    void resetGame() {
        Point2D startPoint = model.getCar().center();
        model.setTestObstacles(null);
        model.getCar().setPosition(100, 100);
        model.resetGame();
        assertEquals(startPoint, model.getCar().center());
    }


    @Test
    void collisionDetectAllGood() {
        createTestObstacles();
        model.setTestObstacles(testObstacles);
        assertFalse(model.collisionDetect());


    }

    @Test
    void collisionDetectOneFailure() {
        createTestObstacles();
        testObstacles.add(new Obstacle(new Point2D(0, 0), 1000, 1000, 0, 0));
        model.setTestObstacles(testObstacles);
        assertTrue(model.collisionDetect());

    }
}