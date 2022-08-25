package Visualizer.Equalizer;

import Visualizer.Player.MP3;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.LinkedList;

public class Equalizer {

    private MP3 player;

    public Equalizer(MP3 player){
        this.player = player;
    }

    public HBox create(){
        LinkedList<Band> bands = new LinkedList<>();
        LinkedList<Label> gain = new LinkedList<>();
        int frequency = 32;

        for(int i = 0; i < player.getPlayer().getAudioEqualizer().getBands().size(); i++){
            Slider slider = new Slider();
            slider.setId("equalizer-slider");
            slider.setValue(50);
            slider.setOrientation(Orientation.HORIZONTAL);
            Label label = new Label(frequency + "hz");
            label.getStyleClass().add("label1");

            if(i != 1){
                frequency = frequency * 2;
            }
            else{
                frequency = (frequency * 2) - 3;
            }

            bands.add(new Band(player.getPlayer().getAudioEqualizer().getBands().get(i),slider,label));
            Label gainLabel = new Label();
            gainLabel.getStyleClass().add("label1");
            gainLabel.setText("0 db");
            gain.add(gainLabel);
        }
        VBox bandSliders = new VBox(15);
        bandSliders.setAlignment(Pos.CENTER);
        VBox bandLabels  = new VBox(7);
        bandLabels.setAlignment(Pos.CENTER);
        VBox gainLabels = new VBox(7);
        gainLabels.setAlignment(Pos.CENTER);

        for(int i = 0; i < bands.size(); i++){
            bandSliders.getChildren().add(bands.get(i).getSlider());
            bandLabels.getChildren().add(bands.get(i).getFrequencyLabel());
            gainLabels.getChildren().add(gain.get(i));
        }

        for(int i = 0; i < bands.size(); i++){
            final int j = i;
            Band band = bands.get(i);
            Slider slider = band.getSlider();
            slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                int index = 0;
                if((newValue.intValue() % 2) == 0 && newValue.intValue() < 50){
                    if(newValue.intValue() == 0){
                        band.getBand().setGain(-24);
                        gain.get(j).setText("-24 db");
                    }
                    int gainValue = -((50 - newValue.intValue()) / 2);
                    band.getBand().setGain(gainValue);
                    gain.get(j).setText(gainValue + " db");

                }else if(newValue.intValue() % 2 == 0 && newValue.intValue() > 50){
                    if(index % 2 == 0){
                        int gainValue = (newValue.intValue() - 50) / 4;
                        band.getBand().setGain(gainValue);
                        gain.get(j).setText(gainValue + " db");
                        index++;
                    }
                    else{
                        index++;
                    }
                }
            });

        }

        HBox left = new HBox(10);
        left.setPadding(new Insets(0,0,0,10));
        left.getChildren().addAll(bandLabels, bandSliders, gainLabels);

        return left;
    }

    public HBox update(MP3 player) {
        this.player = player;
        return create();
    }
}
