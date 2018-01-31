package model;

import GameEnum.Subsoil;
import GameEnum.TimerStates;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;



import java.util.*;


/**
 * The GameModel saves data about the game, including the racecar.
 * It handles most of the calculations for the racegame.
 */
public class GameModel {




    /**
     * The car that is driven on the racetrack
     */
    private Car car;
    /**
     * The obstacles, which the player have to avoid
     */
    private ArrayList<Obstacle> obstacles;
    /**
     * The checkpoints which the player have to cross
     */
    private ArrayList<Checkpoint> checkpoints;


    /**
     * The racetrail
     */
    private Trail racetrail;

    /**
     * The GameField;
     */

    private GameField field;

    /**
     *  the stopWatch
     */
    private TimeCounter timer;

    /**
     * the startPosition
     */
    private Point2D start;




    /**
     * Creates a gameModel, that handles most of the actions
     */
    public GameModel() {
        //initialize Car, default data in GameView
        try {
            car = initializeCar();
            field = new GameField(1000, 500);
            racetrail = new Trail(800, 300, 50, new Point2D(100, 100));
        }catch (IllegalArgumentException e){
            System.err.println("Image path is not correct");
            e.printStackTrace();
        }

        timer = new TimeCounter();

        initializeCheckpoints();
        calcStartPosition();
        setCarToStart();
        newObstacles();




    }


    public void resizeGameField(double width, double height) {
        field.setSize(width, height);
    }


    /**
     * Initializes a car with the initial values
     *
     * @return the initialized car
     */
    private Car initializeCar() {
        //initialize a new car and give it the init values
        car = new Car(0, 0, 35.3, 17.3, 0, 30, 130, 50);
        car.savePosition();
        return car;
    }

    private void calcStartPosition(){
        start = racetrail.getStartPosition();
        start = new Point2D(start.getX() - car.getWidth()*1.1,start.getY());
    }

    private void initializeCheckpoints(){
        checkpoints = new ArrayList<>();



            Ellipse trail = racetrail.getTrailPath();
            Point2D center = racetrail.getCenter();
            double radY = trail.getRadiusY();
            double radX = trail.getRadiusX();


        for (int i = 0; i < 5; i++) {
            checkpoints.add(new Checkpoint(center.getX(),center.getY(),racetrail.getStroke(),0,true));

        }

        checkpoints.get(0).move(0,radY,0);
        checkpoints.get(1).move(radX + racetrail.getStroke() / 2 + 5, 10, 90);
        checkpoints.get(2).move(0,-radY,0);
        checkpoints.get(3).move(-radX + racetrail.getStroke() / 2 + 5, 10, 90);
        checkpoints.get(4).move(0,radY,0);







    }




    /**
     * initialized a number of obstacles randomly
     * you can change the min Size and max Size these are the height AND the width
     * you can't change height and width min/max values seperatly
     * it also rotates the obstacles randomly
     *
     * if the obstacle collides with the car or with an Checkpoint while initializing, it will be deleted and a new obstacle will be created
     *
     * @param numberOfObstacles is the number how many obstacles you want
     * @param maxSize is the maxSize of width AND height of the obstacle
     * @param minSize is the minSize of width AND height of the obstacle
     */

    private void initializeObstacles(int numberOfObstacles, double maxSize, double minSize){
        obstacles = new ArrayList<>();
        Random random = new Random();
        Point2D tmpPoint;
        double tmpHeight;
        double tmpWidth;
        Obstacle tmp;
        double fieldHeight = field.getHeight();
        double fieldWidth = field.getWidth();
        while (obstacles.size() < numberOfObstacles){
            tmpPoint = new Point2D(random.nextDouble() * fieldWidth, random.nextDouble() * fieldHeight);

            while(true){
                tmpHeight = random.nextDouble() * maxSize;
                if(tmpHeight > minSize){
                    break;
                }
            }
            while (true){
                tmpWidth = random.nextDouble() * maxSize;
                if(tmpWidth > minSize){
                    break;
                }
            }
            tmp =new Obstacle(tmpPoint,tmpWidth,tmpHeight, 100, random.nextDouble() * 180);
            if(car != null){
                car.savePosition();
                if(!car.isColliding(tmp)){
                    obstacles.add(tmp);
                }
            }
            if (racetrail.isOnTrail(tmp.center())) {
                if (checkpoints != null) {
                    for (Checkpoint checkpoint : checkpoints
                            ) {
                        if (tmp.isColliding(checkpoint)) {
                            obstacles.remove(tmp);
                        }
                    }
                }
            } else {
                obstacles.remove(tmp);
            }



        }

    }

    /**
     * initialized a number of obstacles randomly
     * if the obstacle collides with the car or with an Checkpoint while initializing, it will be deleted and a new obstacle will be created
     *
     */
    public void newObstacles(){
        initializeObstacles(10, 40 ,30);
    }

    /**
     * help methode to set the car on his start position and resets the Car angle
     */
    private void setCarToStart(){
        car.setCarLocation(start.getX(),start.getY());
        car.resetCarAngle();
    }

    /**
     * sets car back to start
     * sets it's damage and speed to zero
     * resets the time
     * initializes new checkpoints
     *
     */
    public void resetGame(){

        setCarToStart();
        car.setSpeedToZero();
        car.healCar();
        initializeCheckpoints();
        changeTimerState(TimerStates.RESET);
    }

    public GameField getField() {
        return field;
    }

    /**
     * returns the car
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    public Trail getRacetrail() {
        return racetrail;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }



    /**
     * Used to update Car- Position
     */


    public void updateCar(double timeDifferenceInSeconds) {

        updateSubsoil();
        collisionDetect();

        car.updatePosition(timeDifferenceInSeconds);
        this.correctCar();

    }




    /**
     * checks if car is in Gamefield and correct it's position if the car isn't in gamefield
     *
     *
     */
    private void correctCar() {
        field.correctCar(car);
    }





    /**
     * check if last Checkpoint is reached
     *
     *
     */
    public boolean isFinish() {
        boolean isFinish = checkpoints.isEmpty();
        if(isFinish){
            changeTimerState(TimerStates.PAUSE);
        }
        return isFinish;
    }

    public ArrayList<Checkpoint> getCheckpoints() {

        return checkpoints;
    }

    /**
     * check Checkpoints
     */
    public boolean checkCheckpoint() {
        if (!checkpoints.isEmpty()) {
            checkpoints.get(0).setFill(Color.RED);

            if (checkpoints.get(0).getIsReached(car)) {
                changeTimerState(TimerStates.START);


                checkpoints.remove(0);
                return true;
            }
        }
        return false;
    }



    /**
     * update car subsoil
     */
    private void updateSubsoil() {
        if (racetrail.isOnTrail(car.center())) {
            car.setSubsoil(Subsoil.PAVEMENT);
        } else {
            car.setSubsoil(Subsoil.GRAS);
        }
    }


    /**
     * checks all obstacles
     *
     * if the car is to fast and colliding the car is broken
     *
     * if the car is not to fast but colliding the car speed is set to zero
     * and it sets the car to the last position ist is not coliding
     * @return if the car collides with an obstacle
     */


    public boolean collisionDetect() {
        if (!(obstacles == null)) {
            for (Obstacle obstacle : obstacles) {
                if (obstacle.isColliding(car)) {
                    if (obstacle.isDamaged(car.getCurrentSpeed())) {
                        car.setSpeedToZero();
                        car.setBroken(true);

                    } else {
                        car.setSpeedToZero();
                        car.setCarToSavedPosition();
                    }
                    return true;
                }
            }
        }

        car.savePosition();
        return false;
    }

    public boolean hasLost(){
        if(car.isBroken()){
            changeTimerState(TimerStates.PAUSE);
            return true;
        }
        return false;
    }

    /**
     * change the stopwatch state using TimerStates Enum
     * PAUSE : stops the timer
     * START : runs the timer (id didn't start from 0 if you start it while its running)
     * RESET : stops the timer and set its values to 0
     * @param timerStates is the new TimerState
     */

    public void changeTimerState(TimerStates timerStates){
        switch (timerStates){
            case PAUSE:
                timer.stopTimer();
                break;
            case START:
                timer.startTimer();
                break;
            case RESET:
                timer.stopTimer();
                timer.resetTimer();
                break;
                default:
                    System.err.println("False timeState");
        }

    }

    /**
     * returns the stopwatch
     * @return the stopwatch
     */

    public TimeCounter getTimer(){

        return timer;
    }

    /**
     * for testing
     *
     * @param testObstacles are test Obstacles for testing
     */
    public void setTestObstacles(ArrayList<Obstacle> testObstacles) {
        this.obstacles = testObstacles;
    }




}
