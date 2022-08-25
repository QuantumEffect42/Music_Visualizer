package Visualizer;

import Visualizer.Player.MP3;
import Visualizer.Util.Updater;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application{
    public void start(Stage primaryStage) throws Exception{
        Stage window = primaryStage;
        window.setTitle("Music Player Ver 2");
        MP3 player = new MP3();

        Updater updater = Updater.getUpdater();
        updater.setPlayer(player);
        updater.getLayoutMap();

        Scene scene = new Scene(updater.getLayoutMap().init(player));

        Image icon = new Image("C:\\Users\\Nolan\\IdeaProjects\\Music_Visulaizer_3.0\\src\\Visualizer\\Resources\\Icon.png",50,50,true,true);

        window.getIcons().add(icon);
        window.setScene(scene);
        scene.getStylesheets().add("Visualizer_Effects.css");
        window.show();
    }

    public static void main(String[] args){
        launch(args);
    }

//    public static void main(String[] args){
//        launch(args);
//    }
//
//    public void start(Stage primaryStage) throws Exception{
//        File file = new File("C:\\Users\\Nolan\\Music\\Rock\\Bury the Light.mp3");
//        File file = new File("C:\\Users\\Nolan\\Music\\Nightcore\\Au5 - Closer.mp3");
//        Media testMedia = new Media(file.toURI().toString());
//        MediaPlayer player = new MediaPlayer(testMedia);
//        player.play();
//    }

}
