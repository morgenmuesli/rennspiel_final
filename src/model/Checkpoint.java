package model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;



public class Checkpoint extends Box{





    public Checkpoint(double x ,double y,  double length, double angle, boolean originIsCenter) {

            super(x,y,10,length,angle,originIsCenter);
            setFill(Color.WHITE);




    }


    public Checkpoint(double x , double y, double length, double angle, String imagepath, boolean originIsCenter) {

            super(x,y,4,length,angle,originIsCenter);


            setTexture( new ImagePattern(new Image(imagepath)));


    }









    /**
     * this is for move the Line from the origin
     * @param dx is the differenece in x direction
     * @param dy is the differenece in y direction
     * @param rotation is the rotation angle
     */
    public void move(double dx, double dy, double rotation){
        rotate(rotation);
        move(dx,dy);

    }
    public void setCenterLocation(double x , double y){
        setPosition(x,y);
    }



    /**
     * check if car has reached this checkpoint
     * it uses bounding box collision instead of the normal collision to save ressources
     * @param carShape is the shape of the car to check if they intersect
     * @return if car is colliding with the line
     */
    public boolean getIsReached(Box carShape) {

        return boundingBoxCollision(carShape);
    }






}
