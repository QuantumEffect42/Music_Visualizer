package Visualizer.Util;

import Visualizer.Errors.Error;

import java.io.*;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class ConfigReader {

    public String read(){
        String lastDirectory = null;
        Error error = new Error();

        try {
            String classPath = new File(ConfigReader.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toString();
            String filePath = modifyPath(classPath);
            File file = new File(filePath);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            if((lastDirectory = bufferedReader.readLine()) != null){
                File directoryExists = new File(lastDirectory);
                if(directoryExists.exists()){
                    return lastDirectory;
                }
                else{
                    return "C:/";
                }
            }
        }catch (FileNotFoundException e){
            error.newError("Can't find Config file");
        }
        catch(IOException e){
            error.newError("Can't read Config File");
        }
        catch(URISyntaxException e){
            error.newError("Can't parse the .jar path");
        }
        return lastDirectory;
    }

    private String modifyPath(String classPath){
        String filePath = classPath;
        filePath = filePath.replace("\\","/");
        String[] parts = filePath.split("/");
        LinkedList<String> newParts = new LinkedList<>();

        for(int i = 0; i < parts.length-1; i++){
            newParts.add(parts[i]);
        }
        newParts.add("Config");

        filePath = String.join("/", newParts);
        return filePath;
    }
}