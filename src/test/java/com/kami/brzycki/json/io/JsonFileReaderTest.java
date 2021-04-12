package com.kami.brzycki.json.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonFileReaderTest {

    static final String CALENDAR1_PATH = "src/main/resources/dataset/test/file_reader_content_test.json";
    private static JsonFileReader reader;

    @BeforeAll
    public static void setUp() {
        reader = new JsonFileReader();
    }

    @Test
    @DisplayName("Should Pass When Read Data Is Correct")
    public void ShouldPassWhenReadDataIsCorrect() {

        String expected = "{" +
                "\"start\":\"09:00\"," +
                "\"end\":\"10:30\"" +
                "}";
        try {
            String actual = reader.readStringifyJsonFromFile(CALENDAR1_PATH);
            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
}