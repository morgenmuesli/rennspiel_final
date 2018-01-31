package model;


import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;


import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;


/**
 * represents the shape
 */
public abstract class GameObject extends Rectangle {
    private double angle;
    private ImagePattern texture;
    private Color fillColor;
    private Point2D center;
    private Point2D translation = new Point2D(0, 0);


    /**
     * Creates a new instance of Rectangle with the given size.
     *
     * @param width  width of the rectangle
     * @param height height of the rectangle
     */
    public GameObject(double width, double height) {
        super(width, height);
        center = new Point2D(width / 2, height / 2);
    }

    /**
     * Creates a new instance of Rectangle with the given position and size.
     *
     * @param x      horizontal position of the rectangle
     * @param y      vertical position of the rectangle
     * @param width  width of the rectangle
     * @param height height of the rectangle
     */
    public GameObject(double x, double y, double width, double height) {
        super(x, y, width, height);
        center = new Point2D(x + width / 2, y + height / 2);
    }

    /**
     * Creates a new instance of Rectangle with the given position and size.
     *
     * @param x              horizontal position of the rectangle
     * @param y              vertical position of the rectangle
     * @param width          width of the rectangle
     * @param height         height of the rectangle
     * @param originIsCenter true if the position should be the center of the shape
     */
    protected GameObject(double x, double y, double width, double height, boolean originIsCenter) {
        super(x, y, width, height);

        if (originIsCenter) {
            center = new Point2D(x, y);
            move(-width / 2, -height / 2);
        } else {
            center = new Point2D(x + width / 2, y + height / 2);
        }
    }

    /**
     * Creates a new instance of Rectangle with the given position and size.
     *
     * @param x      horizontal position of the rectangle
     * @param y      vertical position of the rectangle
     * @param width  width of the rectangle
     * @param height height of the rectangle
     * @param angle  rotation angle of the rectangle
     */
    protected GameObject(double x, double y, double width, double height, double angle) {
        super(x, y, width, height);
        center = new Point2D(x + width / 2, y + height / 2);
        this.angle = angle;
        this.rotate(angle);
    }

    /**
     * Creates a new instance of GameObject with the given position and size.
     * Also sets a fill color and angle
     *
     * @param x         horizontal position of the rectangle
     * @param y         vertical position of the rectangle
     * @param width     width of the rectangle
     * @param height    height of the rectangle
     * @param angle     rotation angle of the rectangle
     * @param fillColor fillColor of the rectangle
     */
    protected GameObject(double x, double y, double width, double height, double angle, Color fillColor) {
        super(x, y, width, height);
        center = new Point2D(x + width / 2, y + height / 2);
        this.angle = angle;
        this.rotate(angle);
        this.fillColor = fillColor;
    }

    /**
     * Creates a new instance of GameObject with the given position and size.
     * Also sets a fill color and angle
     *
     * @param x       horizontal position of the rectangle
     * @param y       vertical position of the rectangle
     * @param width   width of the rectangle
     * @param height  height of the rectangle
     * @param angle   rotation angle of the rectangle
     * @param texture texture of the rectangle
     */
    protected GameObject(double x, double y, double width, double height, double angle, ImagePattern texture) {
        super(x, y, width, height);
        center = new Point2D(x + width / 2, y + height / 2);
        this.angle = angle;
        this.rotate(angle);
        this.texture = texture;
    }

    /**
     * Creates a new instance of GameObject with the given position and size.
     * Also sets a fill color and angle
     *
     * @param x              horizontal position of the rectangle
     * @param y              vertical position of the rectangle
     * @param width          width of the rectangle
     * @param height         height of the rectangle
     * @param angle          rotation angle of the rectangle
     * @param originIsCenter true if the position should be the center of the shape
     */
    protected GameObject(double x, double y, double width, double height, double angle, boolean originIsCenter) {
        super(x, y, width, height);

        if (originIsCenter) {
            move(-width / 2, -height / 2);
            center = new Point2D(x, y);
        } else {
            center = new Point2D(x + width / 2, y + height / 2);
        }
        this.angle = angle;
        this.rotate(angle);

    }

    /**
     * Creates a new instance of GameObject with the given position and size.
     * Also sets a fill color and angle
     *
     * @param x              horizontal position of the rectangle
     * @param y              vertical position of the rectangle
     * @param width          width of the rectangle
     * @param height         height of the rectangle
     * @param angle          rotation angle of the rectangle
     * @param fillColor      fillColor of the rectangle
     * @param originIsCenter true if the position should be the center of the shape
     */
    protected GameObject(double x, double y, double width, double height, double angle, Color fillColor, boolean originIsCenter) {
        super(x, y, width, height);

        if (originIsCenter) {
            move(-width / 2, -height / 2);
            center = new Point2D(x, y);
        } else {
            center = new Point2D(x + width / 2, y + height / 2);
        }
        this.angle = angle;
        this.rotate(angle);
        this.fillColor = fillColor;
    }

    /**
     * Creates a new instance of GameObject with the given position and size.
     * Also sets a fill color and angle
     *
     * @param x              horizontal position of the rectangle
     * @param y              vertical position of the rectangle
     * @param width          width of the rectangle
     * @param height         height of the rectangle
     * @param angle          rotation angle of the rectangle
     * @param texture        texture of the rectangle
     * @param originIsCenter true if the position should be the center of the shape
     */
    public GameObject(double x, double y, double width, double height, double angle, ImagePattern texture, boolean originIsCenter) {
        super(x, y, width, height);

        if (originIsCenter) {
            move(-width / 2, -height / 2);
        }
        this.angle = angle;
        rotate(angle);
        this.texture = texture;
    }

    /**
     * calculates the shape
     *
     * @return center is the Center of the shape
     */
    public Point2D center() {

        return center.add(translation);
    }


    /**
     * calculates minX using bounds
     *
     * @return minX is the smallest x value
     */

    public double minX() {
        return this.getBoundsInParent().getMinX();
    }

    /**
     * calculates maxX using bounds
     *
     * @return maxX is the biggest x value
     */

    public double maxX() {
        return this.getBoundsInParent().getMaxX();
    }

    /**
     * calculates minY using bounds
     *
     * @return minY is the smallest y value
     */

    public double minY() {
        return this.getBoundsInParent().getMinY();
    }

    /**
     * calculates maxY using bounds
     *
     * @return minY is the biggest y value
     */

    public double maxY() {
        return this.getBoundsInParent().getMaxY();
    }


    /**
     * @return angle is the current rotation
     */

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        rotate(-this.angle);
        rotate(angle);

    }

    public boolean isColliding(GameObject other) {

        Shape me = Shape.union(this, this);
        Shape otherShape = Shape.union(other, other);
        Shape intersect = Shape.intersect(me, otherShape);
        return intersect.getBoundsInLocal().getWidth() != -1;


    }

    /**
     * quick collision check with intersecting bounds,
     * gets from this box and from another box the local bounds and checks if the overlap
     * ATTENTION: THIS COLLISION DETECTION IS NOT ABSOLUTLY ACCURATE SO DON'T USE IT FOR OBSTACLES
     *
     * @param other is the other shape, wich we want to check if we collide
     * @return if they collide
     */

    public boolean boundingBoxCollision(GameObject other) {
        Bounds myBound = this.getBoundsInParent();
        Bounds otherBound = other.getBoundsInParent();
        return myBound.intersects(otherBound);
    }

    /**
     * relocate the GameObject
     * the given Position can be the new Center Position or the new Position of the left corner
     *
     * @param x is the x coordinate
     * @param y is the y coordinate
     */

    public void setPosition(double x, double y) {

        translation = new Point2D(x, y);
        setTranslateX(translation.getX());
        setTranslateY(translation.getY());

    }

    public void setTexture(ImagePattern texture) {
        this.texture = texture;
    }

    /**
     * moves the shape
     *
     * @param dx is change in x direction
     * @param dy is change in y direction
     */

    public void move(double dx, double dy) {
        translation = translation.add(dx, dy);
        setTranslateX(translation.getX());
        setTranslateY(translation.getY());

    }

    public void rotate(double angle) {
        this.angle = this.angle + angle % 360;


        getTransforms().addAll(new Rotate(angle, center.getX(), center.getY()));

    }

    protected Point2D getTranslation() {
        return translation;
    }

}
