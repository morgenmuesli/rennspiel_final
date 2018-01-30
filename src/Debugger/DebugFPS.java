package Debugger;


import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Car;
import model.GameModel;



public class DebugFPS {

    private double timedelta;
    private int frames;
    private double updateevery;
    private Text text;

    public DebugFPS(Pane debugPane) {
        text = new Text();
        text.relocate(10,10);

        timedelta = 0;
        frames = 0;
        updateevery = 20;
        debugPane.getChildren().add(text);
    }

    public void updateVals(double timeDifferenceInSeconds, GameModel gm) {
        Car car = gm.getCar();
        Rectangle carShape = gm.getCar();
        Point2D position = new Point2D(carShape.getX(), carShape.getY());
        if (timedelta >= 2) {
            timedelta = 0;
            frames = 0;
        }
        timedelta += timeDifferenceInSeconds;
        frames++;
        if (frames % updateevery == 0) {
            text.setText(
                    "FPS: " + (int) (frames / timedelta) + "\n" +
                    "CarAngle: " + car.getAngle() + "\n" +
                    "CarSpeed: " + car.getCurrentSpeed() + "\n" +
                    "CarCenter: " + car.center()+ "\n" +
                    "CarPostion: " + position + "\n" +
                    "Subsoil: " + car.getSubsoil() + "\n" +
                    "Obstacle: " + gm.collisionDetect()

            );
        }
    }
}