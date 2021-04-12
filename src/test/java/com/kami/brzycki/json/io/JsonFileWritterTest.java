package com.kami.brzycki.json.io;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonFileWritterTest {
    static final String CREATE_TEST_FILE_PATH = "src/main/resources/dataset/test/file_writter_create_test.json";
    static final String CREATE_CONTENT_TEST_FILE_PATH = "src/main/resources/dataset/test/file_writter_content_test.json";
    private static JsonFileWritter writter;

    @BeforeAll
    public static void setUp() {
        writter = new JsonFileWritter();
    }

    @BeforeEach
    public void cleanUp() {

        File file = new File(CREATE_TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
        file = new File(CREATE_CONTENT_TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    @DisplayName("Should Pass when Create File")
    public void shouldPassWhenCreateFile() {

        writter.writeStringifyJsonToFile("", CREATE_TEST_FILE_PATH);
        File file = new File(CREATE_TEST_FILE_PATH);
        assertTrue(file.exists());
    }

    @Test
    @DisplayName("Should Pass When Created File Contains Correct Content")
    public void shouldPassWhenCreatedFileContainsCorrectContent() {
        String expected =
                "{\"working_hours\":{" +
                        "\"start\":\"09:00\"," +
                        "\"end\":\"19:55\"" +
                        "}," +
                        "\"planned_meeting\":[" +
                        "{" +
                        "\"start\":\"09:00\"," +
                        "\"end\":\"10:30\"" +
                        "}]" +
                        "}";
        try {
            writter.writeStringifyJsonToFile(expected, CREATE_CONTENT_TEST_FILE_PATH);
            String actual = readJsonFile(CREATE_CONTENT_TEST_FILE_PATH);
            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            Assertions.fail();
        }
    }

    private String readJsonFile(String path) throws IOException {
        JsonFileReader reader = new JsonFileReader();
        String json = reader.readStringifyJsonFromFile(path);
        return json;
    }

}