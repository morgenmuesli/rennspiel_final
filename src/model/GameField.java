package model;


import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;


public class GameField extends GameObject {

    private Image texture = new Image("resources/images/grass_texture.png");
    private Color color;

    /**
     * init GameField
     *
     * @param height is the height of the gamefield
     * @param width is the width of the gamefield
     */
    public GameField(int width, int height) {
        super(width,height);
        setFill(new ImagePattern(texture,0,0,64,64,false));
    }



    /**
     * resize Field
     */
    public void setSize(double width, double height) {
        setWidth(width);
        setHeight(height);
    }




    /**
     * if the car collides with the bands of the Game Field, then the position of the car will be changed so it canned be drive outside the map
     * @param car is the car, which is driving on the gamefield
     */
    public void correctCar(Car car) {

        Bounds carBounds = car.getBoundsInParent();

        if(carBounds.getMaxX() > maxX() || carBounds.getMinX() < minX() || carBounds.getMaxY() > maxY() || carBounds.getMinY() < minY()){
            car.setCarToSavedPosition();
           return;
        }

        car.savePosition();

    }




}
