package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Menu {
    private BorderPane menu;
    private VBox vbox;
    private Button startButton;
    private Button resume;
    private Text text;

    /**
     * inits the menu
     *a
     * @param width  is the width of the whole menu (not their elements)
     * @param height is the height of the whole menu (not their elements)
     */

    public Menu(double width, double height) {
        menu = new BorderPane();
        vbox = new VBox();
        menu.setPrefSize(width, height);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: rgba(100,100,100,0.5);;");
        menu.setCenter(vbox);


        startButton = new Button("Start");
        resume = new Button("Fortsetzen");
        resume.setPrefSize(100,40);
        startButton.setPrefSize(100, 40);
        text = new Text();
        text.setFont(new Font(30));
        text.setFill(Color.BLACK);
        text.setTextAlignment(TextAlignment.CENTER);

        vbox.getChildren().add(text);
        vbox.getChildren().add(startButton);


    }

    /**
     * shows a "Good Wishes" Message to the player
     * also changes the button text to "Nochmal"
     *
     * @param time is the time the player needed for the game
     */

    public void showWinningText(String time) {
        hideResumeButton();
        text.setText(String.format("Herzlichen Glückwunsch,\ndeine Zeit war: %s", time));
        startButton.setText("Nochmal!");

    }

    /**
     * shows a "Good Wishes" Message to the player
     * also changes the button text to "Nochmal"
     */

    public void showLostText() {
        hideResumeButton();
        text.setText(String.format("Schade dein Auto ist leider kaputt"));
        startButton.setText("Nochmal!");

    }

    /**
     * shows the pause Menu end enables the resumeButton
     */
    public void showPause() {
        text.setText(String.format("Pause"));
        startButton.setText("Neustart");
        showResumeButton();
    }


    /**
     * returns the Start Button
     *
     * @return startButton
     */
    public Button getStartButton() {
        return startButton;
    }


    /**
     * returns the resume Button
     *
     * @return resumeButton
     */

    public Button getResume() {
        return resume;
    }

    /**
     * shows the Resume Button
     */

    private void showResumeButton() {
        if (!vbox.getChildren().contains(resume)) {
            vbox.getChildren().add(resume);
        }
    }

    /**
     * hides the Resume Button
     */

    private void hideResumeButton() {
        if (vbox.getChildren().contains(resume)) {
            vbox.getChildren().remove(resume);
        }
    }


    /**
     * @return the menu
     */

    public BorderPane getMenu() {
        return menu;
    }
}
