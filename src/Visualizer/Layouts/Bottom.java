package Visualizer.Layouts;

import Visualizer.Player.MP3;
import Visualizer.Sliders.TimeSlider;
import javafx.scene.layout.VBox;


public class Bottom {

    private final MP3 player;
    private TimeSlider timeSlider;

    public Bottom(MP3 player){
        this.player = player;
    }

    public VBox init(){
        VBox bottom = new VBox(10);
        timeSlider = new TimeSlider(player);
        Buttons buttons = new Buttons(player);

        bottom.getChildren().addAll(buttons.create(), timeSlider.create());
        bottom.getStyleClass().add("hbox1");

        return bottom;
    }

    public VBox create(){
        VBox bottom = new VBox(10);
        timeSlider = new TimeSlider(player);
        Buttons buttons = new Buttons(player);

        bottom.getChildren().addAll(buttons.create(), timeSlider.create());
        bottom.getStyleClass().add("hbox1");

        return bottom;
    }

    public TimeSlider getTimeSlider(){
        return timeSlider;
    }

}
