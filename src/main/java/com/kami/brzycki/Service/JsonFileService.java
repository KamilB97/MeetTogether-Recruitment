package com.kami.brzycki.Service;

import com.kami.brzycki.json.io.JsonFileReader;
import com.kami.brzycki.json.io.JsonFileWritter;

import java.io.IOException;

public class JsonFileService {

    private JsonFileReader reader;
    private JsonFileWritter writter;

    public JsonFileService() {
        reader = new JsonFileReader();
        writter = new JsonFileWritter();
    }

    public boolean writeJsonToFile(String json, String path) {
        boolean isSaved = writter.writeJsonToFile(json, path);
        return isSaved;
    }

    public String readJsonFromFile(String path) throws IOException {

        return reader.readJsonFromFile(path);

    }

}
