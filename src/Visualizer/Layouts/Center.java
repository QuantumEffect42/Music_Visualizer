package Visualizer.Layouts;

import Visualizer.Visualizer.Visualizer;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Center {

    public Center(){

    }

    public VBox init(){
        VBox center = new VBox(10);
        return center;
    }

    public VBox create(Node chart){
        VBox center = new VBox(10);

        center.getChildren().addAll(chart);
        center.setAlignment(Pos.CENTER);
        center.getStyleClass().add("chart");
        return center;
    }

    public void configure(BarChart<String, Number> chart, Visualizer visualizer){
        chart.setLegendVisible(false);
        chart.setBarGap(0);
        chart.setCategoryGap(0);
        chart.setVerticalGridLinesVisible(false);
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalZeroLineVisible(false);
        chart.setHorizontalZeroLineVisible(false);
        chart.setMaxSize(1000, 500);
        chart.setMinSize(1000,500);
        chart.setAnimated(false);
        chart.getStyleClass().addAll(".chart",".chart-plot-background", ".default-color0.chart-bar",".default-color0.char-bar");

        visualizer.getyAxis().setTickLabelFormatter(new NumberAxis.DefaultFormatter(visualizer.getyAxis(), null, "dB"));
        visualizer.getyAxis().setTickLabelFill(Color.TRANSPARENT);
        visualizer.getxAxis().setTickLabelFill(Color.TRANSPARENT);

        visualizer.getyAxis().setUpperBound(50);
        visualizer.getyAxis().setLowerBound(-50);
        visualizer.getyAxis().setAutoRanging(false);

        visualizer.getyAxis().setMaxHeight(700);
        visualizer.getyAxis().setPrefHeight(700);
        visualizer.getyAxis().setMinHeight(700);

        chart.getData().add(visualizer.getSeries1());
        chart.getData().add(visualizer.getSeries2());

    }

}
