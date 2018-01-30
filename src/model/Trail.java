package model;


import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.util.ArrayList;

public class Trail {
	private Ellipse trail; //the middle path
	private Ellipse outerEllipse; //the outer path
	private Ellipse innerEllipse; //the inner path
    private double stroke;
    private Point2D position;
    private Point2D center;
    private Point2D startPosition;
    private double height;
    private double width;
    private Color fillColor;
    private Image image = new Image("resources/images/trail.png");




    public Trail(double width, double height, double stroke, Point2D position) {
        this.position = position;
        this.stroke = stroke;
        this.height = height;
        this.width = width;
        center = position.add(width / 2, height / 2);
        initEllipses();
        startPosition = center.add(-10,trail.getRadiusY() );





    }

    /**
     * inits the outer, inner and middle Ellipse
     */
    private void initEllipses(){
        trail = new Ellipse();
        trail.setCenterX(center.getX());
        trail.setCenterY(center.getY());
        trail.setRadiusX(width / 2);
        trail.setRadiusY(height / 2);

        outerEllipse = new Ellipse();
        outerEllipse.setCenterX(center.getX());
        outerEllipse.setCenterY(center.getY());
        outerEllipse.setRadiusX(width / 2 + stroke / 2);
        outerEllipse.setRadiusY(height / 2 + stroke / 2);

        innerEllipse = new Ellipse();
        innerEllipse.setCenterX(center.getX());
        innerEllipse.setCenterY(center.getY());
        innerEllipse.setRadiusX(width / 2 - stroke / 2);
        innerEllipse.setRadiusY(height / 2 - stroke / 2);
    }


	public Ellipse getTrailPath() {
		return trail;

    }

    public double getStroke() {
        return stroke;
    }

    public Point2D getCenter() {
        return center;
    }

    public Point2D getStartPosition() {
        return startPosition;
    }

    /**
     * checks if car is on trail
     * @param carPosition is the center of the car
     * @return true if its on the trail false if it's not
     */
	public boolean isOnTrail(Point2D carPosition) {
        return outerEllipse.contains(carPosition) && !innerEllipse.contains(carPosition);
	}

    /**
     * draws the Trail on Canvas
     *
     */
	public Shape getTrailShape(){
        Shape trail = Path.subtract(outerEllipse,innerEllipse);
        if(fillColor == null && image == null){
            trail.setFill(Color.GRAY);
        }else if(image != null){
            trail.setFill(new ImagePattern(image,0,0,32,32,false));
        }else {
            trail.setFill(fillColor);
        }

        return trail;


    }
}
