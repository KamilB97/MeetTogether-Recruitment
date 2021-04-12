package com.kami.brzycki.service;

import com.kami.brzycki.json.io.JsonFileReader;
import com.kami.brzycki.json.io.JsonFileWritter;
import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.TimeSlot;

import java.io.IOException;
import java.util.List;

public class FileService {
    //private final String OUTPUT_RESULT_PATH = "src/main/resources/dataset/output/result.json";
    private final String OUTPUT_RESULT_PATH = "./data/output/result.json";

    private JsonFileReader reader;
    private JsonFileWritter writter;
    private MappingService mappingService;

    public FileService() {
        this.reader = new JsonFileReader();
        this.writter = new JsonFileWritter();
        this.mappingService = new MappingService();

    }

    public FileService(JsonFileReader reader, JsonFileWritter writter, MappingService mappingService) {
        this.reader = reader;
        this.writter = writter;
        this.mappingService = mappingService;
    }

    public void writeMeetingTermsToJsonFile(List<TimeSlot> timeSlots) throws IOException {

        String json = mappingService.serializeAvailableMeetingTimeSlotsToJson(timeSlots);
        writter.writeStringifyJsonToFile(json, OUTPUT_RESULT_PATH);
    }

    public Calendar readCalendarFromJsonFile(String path) throws IOException {

        String json = reader.readStringifyJsonFromFile(path);
        Calendar calendar = mappingService.deserializeJsonCalendar(json);
        return calendar;
    }

}
