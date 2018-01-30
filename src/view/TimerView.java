package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.TimeCounter;

public class TimerView {

    private Text text;

    public TimerView(Pane timerPane,double x, double padding){

        text = new Text();

        text.setFill(Color.RED);
        text.setFont(new Font( 30));
        text.setText(" -- :: -- :: -- ");

        double height = text.getBoundsInParent().getHeight();
        double width = text.getBoundsInParent().getWidth();

        text.relocate(x - width/2, padding);

        timerPane.getChildren().add(text);




    }


    public void drawTime(TimeCounter timeCounter){
        text.setFill(Color.RED);
        text.setText(timeCounter.toString());

    }


}
