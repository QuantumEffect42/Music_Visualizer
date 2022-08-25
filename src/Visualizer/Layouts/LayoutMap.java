package Visualizer.Layouts;

import Visualizer.Equalizer.Equalizer;
import Visualizer.Player.MP3;
import Visualizer.Util.Updater;
import Visualizer.Visualizer.Visualizer;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.BorderPane;

public class LayoutMap {

    private static LayoutMap instance;
    private Updater updater;
    private Center center;
    private BarChart<String, Number> chart;
    private BorderPane borderPane;
    private Equalizer equalizer;
    private Top top;

    public static LayoutMap getInstance(){
        if (instance == null){
            return new LayoutMap();
        }
        return instance;
    }

    public BorderPane init(MP3 player){
        borderPane = new BorderPane();
        Bottom bottom = new Bottom(player);
        top = new Top(player);
        equalizer = new Equalizer(player);
        center = new Center();

        borderPane.setCenter(center.init());
        borderPane.setBottom(bottom.init());
        borderPane.setTop(top.init());

        borderPane.getStyleClass().add("pane");
        borderPane.setMinSize(1300,700);

        updater = Updater.getUpdater();
        updater.setVisualizer(new Visualizer(player));
        updater.setTimeSlider(bottom.getTimeSlider());

        return borderPane;
    }

    public BorderPane create(MP3 player){

        borderPane = new BorderPane();
        Bottom bottom = new Bottom(player);
        top = new Top(player);
        equalizer = new Equalizer(player);
        center = new Center();

        Visualizer visualizer = new Visualizer(player);
        if(player.getFile() != null) {
            visualizer.create();
            chart = new BarChart<>(visualizer.getxAxis(), visualizer.getyAxis());
            center.configure(chart, visualizer);
            borderPane.setCenter(center.create(chart)); //creates a new center layout and adds the chart to it
            borderPane.setLeft(equalizer.create());
        }

        borderPane.setBottom(bottom.create());
        borderPane.setTop(top.create());

        updater = Updater.getUpdater();
        updater.setVisualizer(visualizer);
        updater.setTimeSlider(bottom.getTimeSlider());

        borderPane.getStyleClass().add("pane");
        return borderPane;
    }

    public void update(MP3 player){
        chart = new BarChart<>(updater.getVisualizer().getxAxis(), updater.getVisualizer().getyAxis());
        center.configure(chart,updater.getVisualizer());
        borderPane.setCenter(center.create(chart));
        borderPane.setLeft(equalizer.update(player));
        borderPane.setTop(top.update(player));
    }

}
