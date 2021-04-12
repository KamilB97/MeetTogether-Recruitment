package com.kami.brzycki.json.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kami.brzycki.error.JsonParseException;
import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.Meeting;
import com.kami.brzycki.model.WorkingHours;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CalendarDeserializerTest {

    @Test
    @DisplayName("Should Throw JsonParseException When cannot Parse Json to Calendar Object")
    public void shouldThrowJsonParseExceptionWhenCannotParseJson() {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Calendar.class, new CalendarDeserializer());
        mapper.registerModule(module);
        String json = "{" +
                "\"start\":\"09:00\"," +
                "\"end\":\"10:30\"" +
                "}";
        Assertions.assertThrows(JsonParseException.class, () -> {
            mapper.readValue(json, Calendar.class);
        });
    }

    @Test
    @DisplayName("Should Retrun Calendar Object When Deserialize")
    public void shouldReturnCalendarWhenDeserialize() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Calendar.class, new CalendarDeserializer());
        mapper.registerModule(module);
        Calendar expectedCalendar = getCalendar();

        Calendar actualCalendar = mapper.readValue(getCalendarJson(), Calendar.class);
        Assertions.assertEquals(expectedCalendar, actualCalendar);

    }

    private Calendar getCalendar() {
        Calendar calendar = new Calendar();
        calendar.setWorkingHours(new WorkingHours("09:00", "19:55"));
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting("09:00", "10:30"));
        meetings.add(new Meeting("12:00", "13:00"));
        meetings.add(new Meeting("16:00", "18:00"));
        calendar.setPlanedMeetings(meetings);

        return calendar;

    }

    private String getCalendarJson() {
        String json =
                " {\"working_hours\": {" +
                        " \"start\": \"09:00\"," +
                        " \"end\": \"19:55\"" +
                        " }," +
                        " \"planned_meeting\": [" +
                        " {" +
                        " \"start\": \"09:00\"," +
                        " \"end\": \"10:30\"" +
                        " }," +
                        " {" +
                        " \"start\": \"12:00\"," +
                        " \"end\": \"13:00\" " +
                        " }," +
                        " {" +
                        " \"start\": \"16:00\"," +
                        " \"end\": \"18:00\"" +
                        " }" +
                        " ]" +
                        "}";
        return json;
    }

}