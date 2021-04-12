package com.kami.brzycki.json.mapping;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.kami.brzycki.error.JsonParseException;
import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.Meeting;
import com.kami.brzycki.model.TimeSlot;
import com.kami.brzycki.model.WorkingHours;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarDeserializer extends StdDeserializer<Calendar> {

    public CalendarDeserializer() {
        this(null);
    }

    public CalendarDeserializer(Class<?> vc) {
        super(vc);
    }


    @SneakyThrows
    @Override
    public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        try {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            String workingHoursStart = node.get("working_hours").toString();

            ObjectMapper mapper = new ObjectMapper();
            WorkingHours mappedWorkingHours = new WorkingHours(mapper.readValue(workingHoursStart, TimeSlot.class));

            String plannedMeetings = node.get("planned_meeting").toString();
            CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, TimeSlot.class);
            List<TimeSlot> meetingTimeSlots = mapper.readValue(plannedMeetings, javaType);

            List<Meeting> mappedPlannedMeetings = meetingTimeSlots
                    .stream()
                    .map(x -> {
                        return new Meeting(x.getStart(), x.getEnd());
                    }).collect(Collectors.toList());

            Calendar calendar = new Calendar(mappedWorkingHours, mappedPlannedMeetings);
            return calendar;
        } catch (Exception e) {

            throw new JsonParseException("Could not parse json calendar");
        }
    }
}
