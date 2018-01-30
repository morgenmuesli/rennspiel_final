package model;


public class TimeCounter {

    private double timeInSeconds;
    private boolean isRunning;



    public TimeCounter( ){
        timeInSeconds = 0;

    }

    public void updateTime(double timeDifferenceInSeconds){
        if(isRunning){
            timeInSeconds += timeDifferenceInSeconds;
        }

    }

    public void startTimer(){
        isRunning = true;
    }

    public void stopTimer(){
        isRunning = false;
    }

    public void resetTimer(){

        timeInSeconds = 0;
    }
    private int calcMilli(){
        return (int)(100 * timeInSeconds) % 100;
    }

    private int calcSeconds(){
        return (int)timeInSeconds % 60;
    }

    private int calcMinutes(){

            return (int) timeInSeconds/60;

    }

    @Override
    public String toString() {
        int minutes = calcMinutes();
        int seconds = calcSeconds();
        int millis = calcMilli();

        return String.format("%02d::%02d::%02d",minutes,seconds,millis);
    }

}
