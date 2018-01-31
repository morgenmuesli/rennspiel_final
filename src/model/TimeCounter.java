package model;


public class TimeCounter {

    private double timeInSeconds;
    private boolean isRunning;



    public TimeCounter( ){
        timeInSeconds = 0;

    }

    /**
     * calculates the new time
     *
     * @param timeDifferenceInSeconds is the time between two frames
     */
    public void updateTime(double timeDifferenceInSeconds){
        if(isRunning){
            timeInSeconds += timeDifferenceInSeconds;
        }

    }

    /**
     * starts the timer
     */
    public void startTimer(){
        isRunning = true;
    }

    /**
     * stops the timer
     */
    public void stopTimer(){
        isRunning = false;
    }

    /**
     * sets te timer to zero
     */
    public void resetTimer(){

        timeInSeconds = 0;
    }

    /**
     * calculates the milliseconds
     * @return the milliseconds subtracted by full seconds and minutes
     */
    private int calcMilli(){
        return (int)(100 * timeInSeconds) % 100;
    }

    /**
     * calculates the seconds
     * @return the seconds subtracted by full minutes
     */
    private int calcSeconds(){
        return (int)timeInSeconds % 60;
    }

    /**
     * calculates the minutes
     * @return the full minutes
     */
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
