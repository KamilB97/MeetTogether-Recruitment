package com.kami.brzycki.service;

import com.kami.brzycki.json.io.JsonFileReader;
import com.kami.brzycki.json.io.JsonFileWritter;
import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.Meeting;
import com.kami.brzycki.model.WorkingHours;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileServiceTest {

    static FileService fileService = null;
    static MappingService mappingService = mock(MappingService.class);
    static JsonFileReader fileReader = mock(JsonFileReader.class);
    static JsonFileWritter fileWritter = mock(JsonFileWritter.class);

    @BeforeAll
    public static void setUp() {

        fileService = new FileService(fileReader, fileWritter, mappingService);
    }


    @Test
    @DisplayName("Should Return Calendar When Path Is Inserted ")
    public void shouldReturnCalendarWhenGivenPath() throws IOException {
        //Given
        String path = "src/main/resources/dataset/output/result.json";
        Calendar calendar = new Calendar();
        calendar.setWorkingHours(new WorkingHours("09:00", "19:55"));
        List<Meeting> meetigns = new ArrayList<>();
        meetigns.add(new Meeting("08:00", "09:20"));
        meetigns.add(new Meeting("09:00", "10:30"));
        meetigns.add(new Meeting("12:00", "13:00"));
        meetigns.add(new Meeting("16:00", "18:00"));
        calendar.setPlanedMeetings(meetigns);

        String json = "{" +
                "\"working_hours\":{" +
                "\"start\":\"09:00\"," +
                "\"end\":\"19:55\"" +
                "},\n" +
                "\"planned_meeting\":[" +
                "{" +
                "\"start\":\"09:00\"," +
                "\"end\":\"10:30\"" +
                "}," +
                "{" +
                "\"start\":\"12:00\"," +
                "\"end\":\"13:00\"\n" +
                "}," +
                "{" +
                "\"start\":\"16:00\"," +
                "\"end\":\"18:00\"" +
                "}" +
                "]}";

        //When
        when(fileReader.readStringifyJsonFromFile(path)).thenReturn(json);
        when(mappingService.deserializeJsonCalendar(json)).thenReturn(calendar);
        //Then

        Assertions.assertEquals(calendar, fileService.readCalendarFromJsonFile(path));

    }
}