package Visualizer.Util;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SingleSongChooser {

    public File open(){
        ConfigReader configReader = new ConfigReader();
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(configReader.read())); //gets the file path from the config reader
        String name = configReader.read();

        return fileChooser.showOpenDialog(stage);
    }

}
