package com.kami.brzycki.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kami.brzycki.json.mapping.CalendarDeserializer;
import com.kami.brzycki.json.mapping.TimeSlotSerializer;
import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.TimeSlot;

import java.util.List;

public class JsonMappingService {


    private ObjectMapper mapper;
    private SimpleModule module;


    public JsonMappingService() {
        mapper = new ObjectMapper();
        module = new SimpleModule();
        module.addDeserializer(Calendar.class, new CalendarDeserializer());
        module.addSerializer(TimeSlot.class, new TimeSlotSerializer());
        mapper.registerModule(module);
    }

    public Calendar deserializeJsonCalendar(String json) {
        Calendar calendar = null;

        try {
            calendar = mapper.readValue(json, Calendar.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    public String serializeAvailableMeetingsToJson(List<TimeSlot> availableMeetingTimeSlots) {

        String returnedJson = "";

        try {
            returnedJson = mapper.writeValueAsString(availableMeetingTimeSlots);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return returnedJson;
    }

}
