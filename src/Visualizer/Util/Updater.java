package Visualizer.Util;

import Visualizer.Layouts.LayoutMap;
import Visualizer.Player.MP3;
import Visualizer.Sliders.TimeSlider;
import Visualizer.Visualizer.Visualizer;

public class Updater {

    private static Updater instance = null;
    private MP3 player;
    private Visualizer visualizer;
    private TimeSlider timeSlider;
    private final LayoutMap layoutMap;
    private ConfigWriter configWriter = new ConfigWriter();

    public static Updater getUpdater(){
        if(instance == null){
            instance = new Updater();
        }
        return instance;
    }

    private Updater(){
        layoutMap = LayoutMap.getInstance();
    }

    public void update(MP3 player){
        visualizer.update(player);
        layoutMap.update(player);
        timeSlider.update(player);
        configWriter.write(player.getPath());
    }

    public ConfigWriter getConfigWriter() {
        return configWriter;
    }

    public void setConfigWriter(ConfigWriter configWriter) {
        this.configWriter = configWriter;
    }

    public Visualizer getVisualizer() {
        return visualizer;
    }

    public void setVisualizer(Visualizer visualizer) {
        this.visualizer = visualizer;
    }

    public TimeSlider getTimeSlider() {
        return timeSlider;
    }

    public void setTimeSlider(TimeSlider timeSlider) {
        this.timeSlider = timeSlider;
    }

    public LayoutMap getLayoutMap() {
        if(player.getFile() == null){
            layoutMap.init(player);
            return layoutMap;
        }
        else {
            layoutMap.create(player);
            return layoutMap;
        }
    }

    public MP3 getPlayer() {
        return player;
    }

    public void setPlayer(MP3 player) {
        this.player = player;
    }
}

