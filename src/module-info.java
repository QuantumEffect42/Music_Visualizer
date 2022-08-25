module musicvisualizerfx{
    requires javafx.controls;
    requires javafx.media;
    requires javafx.graphics;
    requires javafx.fxml;

    opens Visualizer to javafx.fxml;

    exports Visualizer;
}