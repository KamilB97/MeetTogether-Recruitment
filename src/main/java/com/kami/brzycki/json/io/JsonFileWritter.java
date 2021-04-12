package com.kami.brzycki.json.io;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWritter {


    public boolean writeStringifyJsonToFile(String json, String path) throws IOException {

        boolean isFileSaved = false;

        try (FileWriter writer = new FileWriter(path)) {
            writer.write(json);
            isFileSaved = true;
        }
        return isFileSaved;
    }

}
