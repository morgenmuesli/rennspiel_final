package model;



import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Obstacle extends Box{

    private Point2D position;

    private double maxSpeed;
    private Color fillColor;
    private ImagePattern texture = new ImagePattern(new Image("resources/images/obstacles.png"));
    private Color color;

    public Obstacle(Point2D position, double width, double height, double maxSpeed) {


        super(position.getX(), position.getY(), width, height);
        this.maxSpeed = maxSpeed;
        if(texture == null && color == null){
            setFill(Color.BROWN);
        }else if(texture != null){
            setFill(texture);
        }

    }

    public Obstacle(Point2D position, double width, double height, double maxSpeed, double angle) {
        super(position.getX(), position.getY(), width, height,angle);
        this.position = position;
        this.maxSpeed = maxSpeed;
        if(texture == null && color == null){
            setFill(Color.BROWN);
        }else if(texture != null){
            setFill(texture);
        }




    }




    /**
     * check if car is fast enough and would be destroy
     * @param speed is the speed of the car
     * @return if the car would be destroy or not
     */
    public boolean isDamaged(double speed) {
        return speed >= maxSpeed;
    }

}
