package Visualizer.Util;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.LinkedList;

public class QueueChooser {

    public LinkedList<File> open(){
        LinkedList<File> queue;
        ConfigReader configReader = new ConfigReader();
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        String name = configReader.read();
        fileChooser.setInitialDirectory(new File(configReader.read())); //gets the file path from the config reader
        queue = new LinkedList<>(fileChooser.showOpenMultipleDialog(stage));

        if(queue == null){
            return null;
        }
        else {
            return queue;
        }
    }

}
