package Visualizer.Equalizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.EqualizerBand;

public class Band {

    private EqualizerBand band;
    private Slider slider;
    private Label frequencyLabel;

    public Band(EqualizerBand band, Slider slider, Label frequencyLabel){
        this.band = band;
        this.slider = slider;
        this.frequencyLabel = frequencyLabel;
    }

    public EqualizerBand getBand() {
        return band;
    }

    public void setBand(EqualizerBand band) {
        this.band = band;
    }

    public Slider getSlider() {
        return slider;
    }

    public void setSlider(Slider slider) {
        this.slider = slider;
    }

    public Label getFrequencyLabel() {
        return frequencyLabel;
    }

    public void setFrequencyLabel(Label frequencyLabel) {
        this.frequencyLabel = frequencyLabel;
    }
}
