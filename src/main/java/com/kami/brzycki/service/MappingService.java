package com.kami.brzycki.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kami.brzycki.json.mapping.CalendarDeserializer;
import com.kami.brzycki.json.mapping.TimeSlotSerializer;
import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.TimeSlot;

import java.util.List;

public class MappingService {


    private ObjectMapper mapper;
    private SimpleModule module;


    public MappingService() {
        mapper = new ObjectMapper();
        module = new SimpleModule();
        module.addDeserializer(Calendar.class, new CalendarDeserializer());
        module.addSerializer(TimeSlot.class, new TimeSlotSerializer());
        mapper.registerModule(module);
    }

    public Calendar deserializeJsonCalendar(String json) throws JsonProcessingException {
        Calendar calendar = null;
        calendar = mapper.readValue(json, Calendar.class);

        return calendar;
    }

    public String serializeAvailableMeetingTimeSlotsToJson(List<TimeSlot> availableMeetingTimeSlots) throws JsonProcessingException {
        String returnedJson = "";
        returnedJson = mapper.writeValueAsString(availableMeetingTimeSlots);

        return returnedJson;
    }

}
