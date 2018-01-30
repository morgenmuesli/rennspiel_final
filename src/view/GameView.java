package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;

/**
 * Contains every GUI element
 */
public class GameView {

    //The scene where all is stacked up
    private Scene scene;

    //Stackpane, where all dialogs are stacked
    private StackPane rootPane;

    private Pane gamePane;
    public Pane debug;
    private Pane timer;
    private BorderPane menuPane;

    private Canvas canvas;
    private Menu menu;

    private ArrayList<Checkpoint> showingCheckpoints;
    private ArrayList<Obstacle> showingObstacles;


    public Scene getScene() {
        return scene;
    }


    /**
     * GameView object for setting up the GUI
     *
     * @param stage the primary stage
     */
    public GameView(Stage stage) {

        stage.setTitle("Rennspiel Christoph Meyer");
        stage.setResizable(false);
        stage.sizeToScene();

        rootPane = new StackPane();
        scene = new Scene(rootPane, 1000, 500);


        setUpGameWindow();

        stage.setScene(scene);
    }

    /**
     * Sets up the main game window with the course as panebackground,
     * the car in the initial Position
     */
    private void setUpGameWindow() {
        gamePane = new Pane();
        debug = new Pane();
        timer = new Pane();

        menu = new Menu(scene.getWidth(),scene.getHeight());
        menuPane = menu.getMenu();


        canvas = new Canvas(scene.getWidth(), scene.getHeight());
        gamePane.getChildren().add(canvas);
        rootPane.getChildren().add(gamePane);

    }


    /**
     * shows debugPane
     */
    public void showDebugPane(){
        if(!gamePane.getChildren().contains(debug)){
            gamePane.getChildren().add(debug);
        }
    }

    public void hideDebugPane(){
        if(gamePane.getChildren().contains(debug)){
            gamePane.getChildren().remove(debug);
        }
    }

    public void showTime(){
        if(!gamePane.getChildren().contains(timer)){
            gamePane.getChildren().add(timer);
        }
    }

    public void hideTime(){
        if(gamePane.getChildren().contains(timer)){
            gamePane.getChildren().remove(timer);
        }
    }

    public void hideStartMenu(){
        if(gamePane.getChildren().contains(menuPane)){
            gamePane.getChildren().remove(menuPane);
        }
    }

    public void showStartMenu(){
        if(!gamePane.getChildren().contains(menuPane)){
            gamePane.getChildren().add(menuPane);
        }
    }

    public void drawGameObjects(GameModel gameModel){
        drawGameField(gameModel.getField());
        drawTrail(gameModel.getRacetrail());
        drawObstacles(gameModel.getObstacles());
        drawCar(gameModel.getCar());
        drawCheckpoints(gameModel.getCheckpoints());

    }

    private void drawGameField(GameField field){
        if(!gamePane.getChildren().contains(field)){
            gamePane.getChildren().add(field);
        }
    }

    private void drawCar(Car car){
        if(!gamePane.getChildren().contains(car)){
            gamePane.getChildren().add(car);
        }

    }

    private void drawObstacles(ArrayList<Obstacle> obstacles){
        /*
         * delete the old ones
         */
        if(showingCheckpoints != null){
            for (Obstacle showingObstacle:
                    showingObstacles) {
                if(gamePane.getChildren().contains(showingObstacle)){
                    gamePane.getChildren().remove(showingObstacle);
                }
            }
        }

        /*
         * adds the new ones
         */
        showingObstacles = obstacles;
        for (Obstacle obstacle:
             obstacles) {
            if(!gamePane.getChildren().contains(obstacle)){
                gamePane.getChildren().add(obstacle);
            }
        }


    }
    private void drawTrail(Trail trail){
        if(!gamePane.getChildren().contains(trail.getTrailShape())){
            gamePane.getChildren().add(trail.getTrailShape());
        }

    }

    private void drawCheckpoints(ArrayList<Checkpoint> checkpoints){
        /*
         * delete the old ones
         */
        if(showingCheckpoints != null){
            for (Checkpoint showingCheckpoint:
                    showingCheckpoints) {
                if(gamePane.getChildren().contains(showingCheckpoint)){
                    gamePane.getChildren().remove(showingCheckpoint);
                }
            }
        }

        /*
         * adds the new ones
         */
        showingCheckpoints = checkpoints;
        Checkpoint checkpoint;
        for (int i = checkpoints.size() - 1; i >= 0; i--){
            checkpoint = checkpoints.get(i);
            if(!gamePane.getChildren().contains(checkpoint)){
                gamePane.getChildren().add(checkpoint);
            }
        }
    }

    public void updateCheckpoints(ArrayList<Checkpoint> checkpoints){
        drawCheckpoints(checkpoints);
    }

    public void updateObstacles(ArrayList<Obstacle> obstacles){
        drawObstacles(obstacles);
    }










    public Canvas getCanvas() {
        return canvas;
    }

    public Pane getTimerPane(){
        return timer;
    }

    public Menu getMenu() {
        return menu;
    }
}