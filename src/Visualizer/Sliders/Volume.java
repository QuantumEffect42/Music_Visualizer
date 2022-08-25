package Visualizer.Sliders;

import Visualizer.Player.MP3;
import javafx.application.Platform;
import javafx.scene.control.Slider;

public class Volume {

    private final MP3 player;

    public Volume(MP3 player){
        this.player = player;
    }

    public Slider create(){
        Slider volumeSlider = new Slider();
        volumeSlider.setMaxWidth(100);
        volumeSlider.setMinWidth(100);

        volumeSlider.valueProperty().addListener(observable -> {
            if (volumeSlider.isValueChanging()) {
                player.getPlayer().setVolume((volumeSlider.getValue() / 100.0));
                player.setVolume(volumeSlider.getValue());
            }
        });
        volumeSlider.setId("slider");

        Platform.runLater(() -> {
            try {
                if (!volumeSlider.isValueChanging()) {
                    volumeSlider.setValue((int) Math.round(player.getPlayer().getVolume() * 100));
                }
            }catch (NullPointerException e){
                volumeSlider.setValue(100);
            }
        });
        return volumeSlider;
    }

}
