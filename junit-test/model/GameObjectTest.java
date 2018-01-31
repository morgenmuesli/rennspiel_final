package model;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {
    JFXPanel jfxPanel = new JFXPanel();
    GameObject testObject = new GameField(20, 20);


    @Test
    void minX() {
        assertEquals(0, testObject.minX());

    }

    @Test
    void minXAfterMove() {
        testObject.move(20, 20);
        assertEquals(20, testObject.minX());

    }

    @Test
    void maxX() {
        assertEquals(20, testObject.maxX());
    }

    @Test
    void maxXAfterMove() {
        testObject.move(20, 20);
        assertEquals(40, testObject.maxX());
    }

    @Test
    void minY() {
        assertEquals(0, testObject.minX());
    }

    @Test
    void minYAfterMove() {
        testObject.move(20, 20);
        assertEquals(20, testObject.minX());
    }


    @Test
    void maxY() {
        assertEquals(20, testObject.maxY());
    }

    @Test
    void maxYAfterMove() {
        testObject.move(20, 20);
        assertEquals(40, testObject.maxY());
    }


    @Test
    void move() {
        Point2D source = testObject.center();
        testObject.move(20, 20);
        source = source.add(20, 20);
        assertEquals(source, testObject.center());

    }

    @Test
    void rotate() {
        testObject.move(20, 20);
        testObject.rotate(45);
        assertEquals(15, testObject.minX(), 1);
    }
}