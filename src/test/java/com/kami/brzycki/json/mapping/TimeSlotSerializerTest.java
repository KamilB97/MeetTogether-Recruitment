package com.kami.brzycki.json.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kami.brzycki.json.mapping.TimeSlotSerializer;
import com.kami.brzycki.model.TimeSlot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TimeSlotSerializerTest {

    @Test
    public void shouldReturnValidJsonArrayWhenSerialize() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(TimeSlot.class, new TimeSlotSerializer());
        mapper.registerModule(module);

        List<TimeSlot> availableTimeSlots = new ArrayList<>();
        availableTimeSlots.add(new TimeSlot("07:30", "08:00"));
        availableTimeSlots.add(new TimeSlot("10:00", "11:00"));

        String expectedSerializationOutput = "[{\"start\":\"07:30\",\"end\":\"08:00\"},{\"start\":\"10:00\",\"end\":\"11:00\"}]";

        String serializationOutput = mapper.writeValueAsString(availableTimeSlots);
        Assertions.assertEquals(expectedSerializationOutput, serializationOutput);

    }
}