package com.kami.brzycki.service;

import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.TimeSlot;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;


public class CalendarService {

    //    private final String CALENDAR1_PATH = "src/main/resources/dataset/input/calendar1.json";
//    private final String CALENDAR2_PATH = "src/main/resources/dataset/input/calendar2.json";
    private final String CALENDAR1_PATH = "./data/input/calendar1.json";
    private final String CALENDAR2_PATH = "./data/input/calendar2.json";

    private AvailableMeetingService availableMeetingService;
    private FileService fileService;

    public CalendarService() {
        this.availableMeetingService = new AvailableMeetingService();
        this.fileService = new FileService();
    }

    public CalendarService(AvailableMeetingService availableMeetingService, FileService fileService) {
        this.availableMeetingService = availableMeetingService;
        this.fileService = fileService;
    }

    public List<TimeSlot> findAvailableMeetingTimeSlots(Calendar calendar1, Calendar calendar2, LocalTime meetingDuration) throws Exception {

        int meetingDurationInMintes = meetingDuration.getHour() * 60 + meetingDuration.getMinute();
        List<TimeSlot> availableMeetingTerms = null;


        availableMeetingTerms = availableMeetingService.findAvailableMeetingTerms(calendar1, calendar2, meetingDurationInMintes);
        fileService.writeMeetingTermsToJsonFile(availableMeetingTerms);

        return availableMeetingTerms;
    }

    public Calendar readCalendar1() throws IOException {

        return fileService.readCalendarFromJsonFile(CALENDAR1_PATH);

    }

    public Calendar readCalendar2() throws IOException {

        return fileService.readCalendarFromJsonFile(CALENDAR2_PATH);

    }

}
