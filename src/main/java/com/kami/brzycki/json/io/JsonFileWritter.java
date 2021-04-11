package com.kami.brzycki.json.io;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWritter {


    public boolean writeJsonToFile(String json, String path) {
        System.out.println("in write json to file");
        boolean isFileSaved = false;

        try (FileWriter writer = new FileWriter(path)) {
            writer.write(json);
            isFileSaved = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isFileSaved;
    }

}
