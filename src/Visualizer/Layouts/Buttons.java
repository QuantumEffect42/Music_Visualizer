package Visualizer.Layouts;

import Visualizer.Player.MP3;
import Visualizer.Util.QueueChooser;
import Visualizer.Util.Updater;
import Visualizer.Sliders.Volume;
import Visualizer.Util.SingleSongChooser;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.File;
import java.util.LinkedList;
import java.util.Random;


public class Buttons {

    private final MP3 player;
    private boolean shuffle = false;
    private boolean repeat = false;
    private final Updater updater = Updater.getUpdater();

    public Buttons(MP3 player){
        this.player = player;
    }

    public HBox create(){
        HBox hbox = new HBox(10);

        ToggleButton shuffle = new ToggleButton();
        shuffle.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Shuffle Button.png",50,50,true,true)));
        shuffle.getStyleClass().add("button-background");
        ToggleGroup group1 = new ToggleGroup();
        shuffle.setToggleGroup(group1);
        shuffle.setSelected(false);
        group1.selectedToggleProperty().addListener(c -> {
            setShuffle();
            if(this.shuffle){
                shuffle.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Shuffle Button Pressed.png",50,50,true,true)));
            }
            else{
                shuffle.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Shuffle Button.png",50,50,true,true)));
            }
        });

        ToggleButton repeat = new ToggleButton();
        repeat.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Repeat.png",50,50,true,true)));
        repeat.getStyleClass().add("button-background");
        ToggleGroup group2 = new ToggleGroup();
        repeat.setToggleGroup(group2);
        repeat.setSelected(false);
        group2.selectedToggleProperty().addListener(c -> {
            setRepeat();
            if(this.repeat){
                repeat.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Repeat Pressed.png",50,50,true,true)));
            }
            else{
                repeat.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Repeat.png",50,50,true,true)));
            }
        });

        Button skip = new Button();
        skip.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Skip Button.png",50,50,true,true)));
        skip.getStyleClass().add("button-background");
        skip.setOnAction(e -> {
            if(player.getFile() != null) {
                Duration durationOfSong = player.getPlayer().getTotalDuration();
                player.pause();
                this.repeat = false;
                player.getPlayer().seek(durationOfSong);
                player.play();
            }
        });

        Button setToStart = new Button();
        setToStart.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Set to 0 Button.png",50,50,true,true)));
        setToStart.getStyleClass().add("button-background");
        setToStart.setOnAction(e -> {
            if(player.getFile() != null) {
                player.pause();
                player.getPlayer().seek(new Duration(0));
                player.play();
            }
        });

        Button play = new Button();
        play.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Play Button.png",50,50,true,true)));
        play.getStyleClass().add("button-background");
        play.setOnAction(e -> {
            if(player.getFile() != null) {
                if (player.isPlaying()) {
                    player.pause();
                    play.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Play Button.png", 50, 50, true, true)));
                } else {
                    player.play();
                    updater.getTimeSlider().timeBar();
                    play.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Pause Button.png", 50, 50, true, true)));
                }
            }
        });

        Button queue = new Button();
        queue.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Create Queue Button.png",50,50,true,true)));
        queue.getStyleClass().add("button-background");
        queue.setOnAction(e -> {
            if(player.getFile() != null) {
                player.pause();
                QueueChooser queueChooser = new QueueChooser();
                LinkedList<File> songs = queueChooser.open();
                play.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Pause Button.png", 50, 50, true, true)));
                if(songs != null) {
                    playQueue(songs);
                }
            }
            else{
                QueueChooser queueChooser = new QueueChooser();
                LinkedList<File> songs = queueChooser.open();
                play.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Pause Button.png", 50, 50, true, true)));
                if(songs != null) {
                    playQueue(songs);
                }
            }
        });

        Button openFile = new Button();
        openFile.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Open File Button.png",50,50,true,true)));
        openFile.getStyleClass().add("button-background");
        openFile.setOnAction(e -> {
            if(player.getFile() != null) {
                player.pause();
                openFile();
                play.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Pause Button.png", 50, 50, true, true)));
                player.play();
                repeatSong();
            }
            else{
                openFile();
                play.setGraphic(new ImageView(new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Pause Button.png",50,50,true,true)));
                player.play();
                repeatSong();
            }
        });

        hbox.getChildren().addAll(openFile,setToStart,play,skip,queue,shuffle,repeat, new Volume(player).create());
        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }

    private void setShuffle(){
        shuffle = !shuffle;
    }

    private void setRepeat(){
        repeat = !repeat;
    }

    private void playQueue(LinkedList<File> queue){
        if (!shuffle) {
            player.setFile(queue.remove(0));
            updater.update(player);
            player.play();
        } else {
            Random random = new Random();
            int randomIndex = random.nextInt(queue.size());
            player.setFile(queue.remove(randomIndex));
            updater.update(player);
            player.play();
        }
        player.getPlayer().setOnEndOfMedia(() -> {
            if(repeat){
                player.getPlayer().seek(new Duration(0));
                player.play();
            }
            else if (!queue.isEmpty()) {
                player.setPauseTime(new Duration(0));
                playQueue(queue);
            }
        });
    }

    private void openFile(){
        SingleSongChooser singleSongChooser = new SingleSongChooser();
        File file = singleSongChooser.open();
        if(file != null) {
            player.setFile(file);
            updater.update(player);
        }
    }

    private void repeatSong(){
        player.getPlayer().setOnEndOfMedia(() -> {
            if(repeat){
                player.getPlayer().seek(new Duration(0));
                player.play();
            }
        });
    }

}
