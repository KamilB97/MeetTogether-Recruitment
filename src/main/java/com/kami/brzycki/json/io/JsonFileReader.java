package com.kami.brzycki.json.io;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class JsonFileReader {

    JSONParser parser = new JSONParser();

    public String readJsonFromFile(String path) throws IOException {

        try {
            Object object = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) object;
            return jsonObject.toJSONString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Could not read file");
        }


    }

}
