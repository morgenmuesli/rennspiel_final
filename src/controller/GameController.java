package controller;

import Debugger.DebugFPS;
import GameEnum.GameState;
import GameEnum.TimerStates;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.*;
import view.GameView;
import view.TimerView;

import java.util.ArrayList;

public class GameController {

    private GameModel gameModel;
    private GameView gameView;
    private Scene scene;
    private GraphicsContext gc;
    private Canvas canvas;
    private GameState state;
    private ArrayList<String> input = new ArrayList<>();

    private boolean firstRound = true;


    private DebugFPS debugFPS;


    private TimerView timerView;

    public GameController(GameModel gameModel, GameView gameView) {
        this.gameView = gameView;

        this.gameModel = gameModel;
        this.scene = gameView.getScene();
        this.canvas = gameView.getCanvas();
        this.gc = canvas.getGraphicsContext2D();
        state = GameState.START;
        gameModel.resizeGameField(canvas.getWidth(), canvas.getHeight());
        debugFPS = new DebugFPS(gameView.debug);
        timerView = new TimerView(gameView.getTimerPane(), canvas.getWidth() / 2, 30);
        gameView.drawGameObjects(gameModel);


        //Set up keylistener
        setUpInputHandler();


    }

    /**
     * Updates all needed dependencies every frame
     *
     * @param timeDifferenceInSeconds the time passed since last frame
     */
    public void updateContinuously(double timeDifferenceInSeconds) {


        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


        switch (state) {
            case START:
                gameView.showStartMenu();
                break;
            case RACE:


                gameView.hideStartMenu();

                gameModel.updateCar(timeDifferenceInSeconds);
                gameModel.checkCheckpoint();

                gameView.updateCheckpoints(gameModel.getCheckpoints());


                // checks all relevant keys

                if (input.contains("UP") || input.contains("W")) {
                    gameModel.getCar().accelerate(timeDifferenceInSeconds);
                }
                if (input.contains("DOWN") || input.contains("S")) {
                    gameModel.getCar().breakCar(timeDifferenceInSeconds);
                }
                if (input.contains("LEFT") || input.contains("A")) {
                    gameModel.getCar().steerCar(-timeDifferenceInSeconds);
                }
                if (input.contains("RIGHT") || input.contains("D")) {
                    gameModel.getCar().steerCar(timeDifferenceInSeconds);
                }
                if (input.contains("TAB")) {
                    gameView.hideDebugPane();
                }
                if (input.contains("R")) {
                    gameModel.resetGame();
                }
                break;
            case PAUSE:
                gameModel.changeTimerState(TimerStates.PAUSE);
                gameView.showStartMenu();
                gameView.getMenu().showPause();
                break;
            case WINNING:
                gameView.showStartMenu();
                gameView.hideTime();
                gameView.getMenu().showWinningText(gameModel.getTimer().toString());
                break;
            case LOOSING:
                gameView.showStartMenu();
                gameView.hideTime();
                gameView.getMenu().showLostText();
                break;


        }


        debugFPS.updateVals(timeDifferenceInSeconds, gameModel);


        gameModel.getTimer().updateTime(timeDifferenceInSeconds);


        timerView.drawTime(gameModel.getTimer());

        setGameState();


    }


    /**
     * check if the player has won or loose and sets the GameState
     */
    private void setGameState() {
        if (gameModel.isFinish()) {

            state = GameState.WINNING;
        }
        if (gameModel.hasLost()) {
            state = GameState.LOOSING;
        }

    }

    /**
     * setup controls for input
     * also enable/disable Keys (except 'P' and 'Escape') while pause the game
     */

    private void setUpInputHandler() {
        /*
         * Useful actions:
         * setOnKeyPressed, setOnKeyReleased
         */
        scene.setOnKeyPressed(event -> {
            String key = event.getCode().toString();
            if (!input.contains(key)) {
                if (key.equals("P") || key.equals("ESCAPE")) {
                    if (state == GameState.RACE) {
                        state = GameState.PAUSE;
                        gameModel.changeTimerState(TimerStates.PAUSE);
                    } else if (state == GameState.PAUSE) {
                        state = GameState.RACE;
                        gameModel.changeTimerState(TimerStates.START);
                    }
                } else {
                    input.add(key);
                }
            }


        });

        scene.setOnKeyReleased(event -> {
            String key = event.getCode().toString();
            input.remove(key);
        });


        setUpStartButton();
        setUpResumeButton();


    }

    /**
     * sets the function of the start button
     */

    private void setUpStartButton() {
        gameView.getMenu().getStartButton().setOnMouseClicked(event -> {
            state = GameState.RACE;
            gameModel.resetGame();
            gameView.hideStartMenu();
            if (!firstRound) {
                gameModel.newObstacles();
            }
            firstRound = false;
            gameView.showTime();
            gameView.updateObstacles(gameModel.getObstacles());
        });
    }

    /**
     * sets the function of the resume button
     */
    private void setUpResumeButton() {
        gameView.getMenu().getResume().setOnMouseClicked(event -> {
            state = GameState.RACE;
            gameModel.changeTimerState(TimerStates.START);

            gameView.hideStartMenu();
        });

    }


}
