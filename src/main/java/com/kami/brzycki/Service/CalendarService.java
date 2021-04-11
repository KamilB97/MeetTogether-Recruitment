package com.kami.brzycki.Service;

import com.kami.brzycki.algorithm.MeetTogetherAlgorithm;
import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.TimeSlot;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
public class CalendarService {

    private final MeetTogetherAlgorithm algorithm;


    public List<TimeSlot> getAvailableMeetingTimeSlots(Calendar calendar1, Calendar calendar2, LocalTime meetingDuration) throws Exception {

        int meetingDurationInMintes = meetingDuration.getHour() * 60 + meetingDuration.getMinute();

        List<TimeSlot> availableMeetingTerms = algorithm.findAvailableMeetingTerms(calendar1, calendar2, meetingDurationInMintes);

        return availableMeetingTerms;
    }

}
