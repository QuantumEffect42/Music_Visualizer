package Visualizer.Sliders;

import Visualizer.Player.MP3;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Duration;


public class TimeSlider {

    private MP3 player;
    private Duration duration;
    private Slider timeSlider;
    private final Label playerDuration;
    private final Label playTime;

    public TimeSlider(MP3 player){
        this.player = player;
        playerDuration = new Label();
        playTime = new Label();
    }

    public HBox create(){
        timeSlider = new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(300);
        timeSlider.setMaxWidth(300);
        timeSlider.setId("slider");

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(playTime,timeSlider,playerDuration);

        return hBox;
    }

    public void timeBar(){
        if(timeSlider != null) {
            duration = player.getPlayer().getMedia().getDuration();
            player.getPlayer().currentTimeProperty().addListener(observable -> {
                updateValues();
            });
            player.getPlayer().setOnReady(() -> {
                duration = player.getPlayer().getMedia().getDuration();
                updateValues();
            });

            timeSlider.valueProperty().addListener(observable -> {
                if(timeSlider.isValueChanging()) {
                    player.getPlayer().seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            });
        }
    }

    private void updateValues() {

        if(playTime != null && timeSlider != null){
            Platform.runLater(() -> {
                Duration currentTime = player.getPlayer().getCurrentTime();
                formatTime(currentTime,duration);
                timeSlider.setDisable(duration.isUnknown());

                if(!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()){
                    timeSlider.setValue((currentTime.divide(duration).toMillis()) * 100.0);
                }
            });
        }

    }
    private void formatTime(Duration currentTime, Duration totalTime) {
        int intElapsed = (int) Math.floor(currentTime.toSeconds());
        int elapsedHours = intElapsed / 3600;

        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 3600;
        }

        int elapsedMins = intElapsed / 60;
        int elapsedSecs = intElapsed - (elapsedHours * 3600) - (elapsedMins * 60);

        if (totalTime.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(totalTime.toSeconds());
            int durationHours = intDuration / 3600;

            if (durationHours > 0) {
                intDuration -= durationHours * 3600;
            }

            int durationMins = intDuration / 60;
            int durationSecs = intDuration - (durationHours * 3600) - (durationMins * 60);

            if (durationHours > 0) {
                playerDuration.setText(String.format("%d:%02d:%02d", durationHours, durationMins, durationSecs));
                playTime.setText(String.format("%d:%02d:%02d", elapsedHours, elapsedMins, elapsedSecs));
            } else {
                playerDuration.setText(String.format("%02d:%02d", durationMins, durationSecs));
                playTime.setText((String.format("%02d:%02d", elapsedMins, elapsedSecs)));
            }
        } else {
            if (elapsedHours > 0) {
                playTime.setText(String.format("%d:%02d:%02d", elapsedHours, elapsedMins, elapsedSecs));
            } else {
                playTime.setText(String.format("%02d:%02d", elapsedMins, elapsedSecs));
            }
        }
    }

    public void update(MP3 player) {
        this.player = player;
        timeBar();
    }
}
