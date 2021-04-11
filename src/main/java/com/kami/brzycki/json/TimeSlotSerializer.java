package com.kami.brzycki.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.kami.brzycki.model.TimeSlot;

import java.io.IOException;

public class TimeSlotSerializer extends StdSerializer<TimeSlot> {

    public TimeSlotSerializer() {
        this(null);
    }

    public TimeSlotSerializer(Class<TimeSlot> t) {
        super(t);
    }

    @Override
    public void serialize(TimeSlot timeSlot, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("start", timeSlot.getStart().toString());
        jsonGenerator.writeObjectField("end", timeSlot.getEnd().toString());

        jsonGenerator.writeEndObject();


    }


}
