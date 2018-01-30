package model;

import GameEnum.Subsoil;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


/**
 * Class car represents the race-car in the race game.
 */
public class Car extends Box{
    private double currentSpeed; //real speed
    private double virtualSpeed; //speed without resistance

    private double accelerateConst;
    private double breakConst = 90;
    private double steeringAngleInSeconds = 90;
    private double maxSpeed;
    private double maxBackSpeed;
    private Point2D positionNotCollided;
    private double startAngle;

    //car constants
    private final double ROLLINGRESISTANCETROAD = 0.015;

    private final double ROLLINGRESISTANCEDIRT = 0.01;
    private final double ROLLINGRESISTANCECONST = 9.81;
    private final double AIRRESISTANCECONST = 0.28 * 2.19 * 0.5 * 1.2041;
    private final double MASS = 1000;



    private boolean broken;
    private Subsoil subsoil;


    private Image texture = new Image("resources/images/car.png");;
    private Image brokenTexture = new Image("resources/images/explode.png");

    public Car(double x, double y, double width, double height, double currentAngle,double accelerateConst,  double maxSpeed, double maxBackSpeed) throws IllegalArgumentException{
        super(x,y,width,height,currentAngle);
        setFill(new ImagePattern(texture));
        startAngle = currentAngle;

        this.accelerateConst = accelerateConst;
        this.maxSpeed = maxSpeed;
        this.maxBackSpeed = maxBackSpeed;


        subsoil = Subsoil.GRAS;


        broken = false;
        currentSpeed = 0;




    }


    /**
     * calculates new Speed
     * uses AirResistance and Rolling Resistance
     */
    public void accelerate(double timeDifferenceInSecconds) {
        calculatesRealSpeed(timeDifferenceInSecconds);
        virtualSpeed = currentSpeed;
        virtualSpeed = virtualSpeed + accelerateConst * timeDifferenceInSecconds;
        if (virtualSpeed >= maxSpeed) {
            virtualSpeed = maxSpeed;
        }
        if (virtualSpeed <= maxBackSpeed) {
            virtualSpeed = maxBackSpeed;
        }


    }

    /**
     * for breaking the Car with an different Constant as the accelarateConst.
     * The break is stronger than accelerate
     * if the speed is lesser than zero it uses the accelerate function with inverted time.
     *
     * @param timeDifferenceInSeconds is the time between two frames
     */

    public void breakCar(double timeDifferenceInSeconds){
        virtualSpeed = currentSpeed;
        if(virtualSpeed <= 0){
            accelerate(-timeDifferenceInSeconds);
        }else {
            virtualSpeed = virtualSpeed - breakConst * timeDifferenceInSeconds;
        }
    }


    /**
     * Calculates Speed reduction
     */
    private void calculatesRealSpeed(double timeDifferenceInSeconds) {




        double rollingresistance;


        switch (subsoil) {
            case PAVEMENT:
                rollingresistance = ROLLINGRESISTANCETROAD;
                break;
            case GRAS:
                rollingresistance = ROLLINGRESISTANCEDIRT;
                break;
            default:
                rollingresistance = ROLLINGRESISTANCEDIRT;
        }
        double deccel = ((rollingresistance * ROLLINGRESISTANCECONST) + (AIRRESISTANCECONST * (Math.pow(virtualSpeed, 2)) / MASS))
                * timeDifferenceInSeconds;

        if (virtualSpeed > deccel) {
            virtualSpeed -= deccel;
        } else if (virtualSpeed < -deccel) {
            virtualSpeed += deccel;
        }else {
            virtualSpeed = 0;
        }

        currentSpeed = virtualSpeed;
    }



    /**
     * set Subsoil
     *
     * @param currentSubsoil is an Enum and can be gras or pavement
     */
    public void setSubsoil(Subsoil currentSubsoil) {
        this.subsoil = currentSubsoil;
    }

    /**
     * @return currentSpeed
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }


    public void healCar(){
        broken = false;
        setFill(new ImagePattern(texture));
    }

    /**
     * set Speed to Zero
     */
    public void setSpeedToZero() {
        virtualSpeed = 0;
        currentSpeed = 0;
    }

    /**
     * returns maxSpeed from car
     *
     * @return maxSpeed
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * return if car is broken
     *
     * @return broken
     */
    public boolean isBroken() {
        return broken;
    }

    /**
     * setBroke
     */
    public void setBroken(boolean broken) {
        this.broken = broken;
        if(broken){
            setFill(new ImagePattern(brokenTexture));
        }
    }












    /**
     * relocate the car with the new Car postion
     * @param x is the new x Position
     * @param y is the new y Position
     */
    public void setCarLocation(double x, double y) {
        setPosition(x,y);

    }

    /**
     * used to steer the car and changed the curentAngle
     * if the user want to drive left -> steeringAngle < 0
     * if the user want to drive right -> steeringAngle > 0
     * @param timeDifferenceInSeconds is the time difference between two frames
     *
     */
    public void steerCar(double timeDifferenceInSeconds) {
        double steeringAngle = timeDifferenceInSeconds * steeringAngleInSeconds;
        if (!broken) {
            rotate(steeringAngle);
        }

    }

    public Subsoil getSubsoil() {
        return subsoil;
    }

    /**
     * calculates next Position
     *
     * @param timeDifferenceInSeconds is the time difference between two frames
     */
    public void updatePosition(double timeDifferenceInSeconds) {
        /*
           calculates Distance with timeDifferenceInSeconds and currentSpeed
         */
        if (!broken) {
            calculatesRealSpeed(timeDifferenceInSeconds);
            double reachedDistance = currentSpeed * timeDifferenceInSeconds;
            double dy = (Math.sin(Math.toRadians(getAngle())) * reachedDistance);
            double dx = (Math.cos(Math.toRadians(getAngle())) * reachedDistance);

            move(dx,dy);
        }



    }

    /**
     * saves the Current Position
     */

    public void savePosition(){
        positionNotCollided = getTranslation();
    }

    /**
     * resets the car to the last saved Position
     */

    public void setCarToSavedPosition(){
        setCarLocation(positionNotCollided.getX(),positionNotCollided.getY());
    }

    public void resetCarAngle(){
        setAngle(startAngle);
    }




}
