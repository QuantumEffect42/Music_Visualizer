package Visualizer.Visualizer;

import Visualizer.Player.MP3;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Visualizer {

    private MP3 player;
    CategoryAxis xAxis;
    NumberAxis yAxis;
    XYChart.Series<String, Number> series1;
    XYChart.Series<String, Number> series2;

    public Visualizer(MP3 player) {
        this.player = player;
    }

    public void update(MP3 player) {
        this.player = player;
        create();
    }

    public void create() {
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();

        //top bars of the visualizer

        XYChart.Data[] series1Data;
        series1 = new XYChart.Series<>();
        series1.setName("Series1");
        series1Data = new XYChart.Data[512];
        if (series1.getData().size() != 0) {
            series1.getData().remove(0, series1Data.length); //removes all of the data from the chart when there is a new song that is made
        }

        for (int i = 0; i < series1Data.length; i++) {
            series1Data[i] = new XYChart.Data(Integer.toString(i + 1), 0); //sets 512 bars and sets the default y value to 0
            series1.getData().add(series1Data[i]); //adds all of the categories to the chart
        }

        //bottom bars of the visualizer

        XYChart.Data[] series2Data;
        series2 = new XYChart.Series<>();
        series2.setName("Series2");
        series2Data = new XYChart.Data[512];
        if (series2.getData().size() != 0) {
            series2.getData().remove(0, series1Data.length);
        }

        for (int i = 0; i < series2Data.length; i++) {
            series2Data[i] = new XYChart.Data(Integer.toString(i + 1), 0);
            series2.getData().add(series2Data[i]);
        }

        player.getPlayer().setAudioSpectrumListener((double timestamp, double duration, float[] magnitudes, float[] phases) -> {
            player.getPlayer().setAudioSpectrumNumBands(256);
            player.getPlayer().setAudioSpectrumInterval(0.01); //sets the update time
            float[] reversedMagnitudes = new float[magnitudes.length];
            float[] allMagnitudes = new float[2 * magnitudes.length];
            int index = magnitudes.length;

            for (int i = 0; i < magnitudes.length; i++) {
                reversedMagnitudes[index - 1] = magnitudes[i];
                index--;
            }
            for (int i = 0; i < magnitudes.length; i++) {
                allMagnitudes[i] = reversedMagnitudes[i];
            }
            index = 0;
            for (int i = magnitudes.length; i < 2 * magnitudes.length; i++) { //setes the largest bars to the centre
                allMagnitudes[i] = magnitudes[index];
                index++;
            }

            for (int i = 0; i < 2 * magnitudes.length; i++) { //adds the bars to both the top and the bottom
                series1Data[i].setYValue(allMagnitudes[i] - player.getPlayer().getAudioSpectrumThreshold());
                series2Data[i].setYValue(-(allMagnitudes[i] - player.getPlayer().getAudioSpectrumThreshold()));
            }
        });

    }

    public CategoryAxis getxAxis() {
        return xAxis;
    }

    public NumberAxis getyAxis() {
        return yAxis;
    }

    public XYChart.Series<String, Number> getSeries1() {
        return series1;
    }

    public XYChart.Series<String, Number> getSeries2() {
        return series2;
    }
}
