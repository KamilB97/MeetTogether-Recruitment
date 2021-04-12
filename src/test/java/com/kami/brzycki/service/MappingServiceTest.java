package com.kami.brzycki.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kami.brzycki.json.io.JsonFileReader;
import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.Meeting;
import com.kami.brzycki.model.TimeSlot;
import com.kami.brzycki.model.WorkingHours;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class MappingServiceTest {
    static final String CALENDAR1_PATH = "src/main/resources/dataset/test/calendar1.json";
    private static MappingService mappingService;

    @BeforeAll
    public static void setUp() {
        mappingService = new MappingService();
    }


    @Test
    @DisplayName("Should Return Calendar Object When Deserialize Json")
    public void shouldReturnCalendarWhenDeserializeJson() throws IOException {

        String calendarJson = getCalendar();
        Calendar expected = new Calendar();
        expected.setWorkingHours(new WorkingHours("09:00", "19:55"));
        List<Meeting> meetigns = new ArrayList<>();
        meetigns.add(new Meeting("09:00", "10:30"));
        meetigns.add(new Meeting("12:00", "13:00"));
        meetigns.add(new Meeting("16:00", "18:00"));
        expected.setPlanedMeetings(meetigns);

        Calendar actual = mappingService.deserializeJsonCalendar(calendarJson);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should Return Stringify Json Array When Serialize Time Slots List")
    public void shouldReturnStringifyJsonArrayWhenSerializeTimeSlots() throws JsonProcessingException {
        //Given
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("09:30", "10:00"));
        timeSlots.add(new TimeSlot("11:30", "12:00"));
        timeSlots.add(new TimeSlot("13:30", "15:00"));
        String expected = "[" +
                "{\"start\":\"09:30\"," +
                "\"end\":\"10:00\"}," +
                "{\"start\":\"11:30\"," +
                "\"end\":\"12:00\"}," +
                "{\"start\":\"13:30\"," +
                "\"end\":\"15:00\"}" +
                "]";
        //When
        String actual = mappingService.serializeAvailableMeetingTimeSlotsToJson(timeSlots);
        //Then
        Assertions.assertEquals(expected, actual);

    }

    private String getCalendar() throws IOException {
        return readJsonFile(CALENDAR1_PATH);
    }

    private String readJsonFile(String path) throws IOException {
        JsonFileReader reader = new JsonFileReader();
        String json = reader.readStringifyJsonFromFile(path);
        return json;
    }


}