package Visualizer.Util;

import Visualizer.Errors.Error;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class ConfigWriter {

    public void write(String path){

        Error error = new Error();
        path = modifyPath(path);
        try {
            String configPath = new File(ConfigWriter.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toString();
            configPath = modifyPath(configPath) + "/Config";
            File configFile = new File(configPath);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(configFile));
            bufferedWriter.write(path);
            bufferedWriter.close();
        }catch(IOException e){
            error.newError("Can't write to Config file");
        }catch (URISyntaxException e){
            error.newError("Can't parse the .jar path");
        }
    }

    private String modifyPath(String path){
        path = path.replace("\\","/");
        String[] parts = path.split("/");
        String newPath = parts[0];

        for(int i = 1; i < parts.length-1; i++){
            newPath = newPath + "/" + parts[i];
        }

        return newPath;
    }

}
