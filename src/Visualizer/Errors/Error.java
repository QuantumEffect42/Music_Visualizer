package Visualizer.Errors;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Error {

    public void newError(String errorMessage){
        Stage stage = new Stage();
        stage.setTitle("Error");

        VBox layout = new VBox();
        Label label = new Label(errorMessage);
        layout.getChildren().add(label);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

}
