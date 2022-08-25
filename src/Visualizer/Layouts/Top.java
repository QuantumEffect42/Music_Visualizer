package Visualizer.Layouts;

import Visualizer.Player.MP3;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Top {

    MP3 player;
    Label name;

    public Top(MP3 player){
        this.player = player;
        name = new Label();
    }

    public HBox init(){
        HBox top = new HBox(10);
        name = new Label();

        top.getChildren().add(name);
        top.getStyleClass().add("hbox0");
        return top;
    }

    public HBox create(){
        HBox top = new HBox(10);
        name = new Label();
        name.setText(player.getName());

        top.getChildren().add(name);
        top.getStyleClass().add("hbox0");
        return top;
    }

    public HBox update(MP3 player){
        this.player = player;
        name.setText(this.player.getName());
        return create();
    }

}
