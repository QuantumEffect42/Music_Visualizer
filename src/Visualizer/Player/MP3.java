package Visualizer.Player;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MP3 {

    private File file;
    private Media media;
    private MediaPlayer player;
    private boolean isPlaying;
    private double volume = 100.0;

    private String path;
    private String name;
    private Duration pauseTime;

    public MP3(){
        file = null;
        media = null;
        player = null;
        path = "";
        name = "";
    }

    public MP3(File file){
        this.file = file;
        path = this.file.getPath();
        media = new Media(file.toURI().toString());
        player = new MediaPlayer(media);
        isPlaying = false;
        pauseTime = new Duration(0);

    }

    public void play(){
        player.play();
        isPlaying = true;
    }
    public void pause(){
        player.pause();
        isPlaying = false;
        pauseTime = player.getCurrentTime();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        path = this.file.getPath();
        media = new Media(this.file.toURI().toString());
        player = new MediaPlayer(media);
        player.setVolume(volume);
        name = getName();
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        player.setOnEndOfMedia(()-> isPlaying = false);
        isPlaying = playing;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        path = path.replace("\\", "/");
        String[] pathSegments = path.split("/");
        for(int i = 0; i < pathSegments.length; i++){
            if(i == (pathSegments.length - 1)){
                name = pathSegments[i];
            }
        }
        String[] nameSegments = name.split(".mp3");
        name = nameSegments[0];
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(Duration pauseTime) {
        this.pauseTime = pauseTime;
    }


    public void setVolume(double volume) {
        this.volume = volume/100.0;
    }
}

