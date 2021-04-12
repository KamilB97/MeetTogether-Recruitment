package com.kami.brzycki.json.io;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonFileReader {

    JSONParser parser = new JSONParser();

    public String readStringifyJsonFromFile(String path) throws IOException {

        try {
            Object object = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            return jsonObject.toJSONString();
        } catch (ParseException e) {
            throw new IOException("Could not parse file to json");
        }
    }
}
