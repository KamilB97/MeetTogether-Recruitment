package com.kami.brzycki.service;

import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.Meeting;
import com.kami.brzycki.model.TimeSlot;
import com.kami.brzycki.model.WorkingHours;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

class CalendarServiceTest {

    static final String CALENDAR1_PATH = "./data/input/calendar1.json";
    static final String CALENDAR2_PATH = "./data/input/calendar2.json";

    static CalendarService calendarService = null;
    static AvailableMeetingService availableMeetingService = mock(AvailableMeetingService.class);
    static FileService fileService = mock(FileService.class);

    @BeforeAll
    public static void setUp() {
        calendarService = new CalendarService(availableMeetingService, fileService);
    }

    @Test
    @DisplayName("Should Return Meeting Time Slots When Called")
    public void shouldRetrunMeetingTimeSlotsWhenCalled() throws Exception {

        //Given
        int meetingDurationMinutes = 30;
        LocalTime meetingDuration = LocalTime.parse("00:30");
        Calendar calendar1 = new Calendar();
        calendar1.setWorkingHours(new WorkingHours("09:00", "19:55"));
        List<Meeting> meetigns1 = new ArrayList<>();
        meetigns1.add(new Meeting("08:00", "09:20"));
        meetigns1.add(new Meeting("09:00", "10:30"));
        meetigns1.add(new Meeting("12:00", "13:00"));
        meetigns1.add(new Meeting("16:00", "18:00"));
        calendar1.setPlanedMeetings(meetigns1);

        Calendar calendar2 = new Calendar();
        calendar2.setWorkingHours(new WorkingHours("10:00", "18:30"));
        List<Meeting> meetigns2 = new ArrayList<>();
        meetigns2.add(new Meeting("10:00", "11:30"));
        meetigns2.add(new Meeting("12:30", "14:30"));
        meetigns2.add(new Meeting("14:30", "15:00"));
        meetigns2.add(new Meeting("16:00", "17:00"));
        calendar2.setPlanedMeetings(meetigns2);

        List<TimeSlot> expectedTimeSlotList = new ArrayList<>();
        expectedTimeSlotList.add(new TimeSlot("11:30", "12:00"));
        expectedTimeSlotList.add(new TimeSlot("15:00", "15:30"));
        expectedTimeSlotList.add(new TimeSlot("15:05", "15:35"));
        expectedTimeSlotList.add(new TimeSlot("15:10", "15:40"));
        expectedTimeSlotList.add(new TimeSlot("15:15", "15:45"));
        expectedTimeSlotList.add(new TimeSlot("15:20", "15:50"));
        expectedTimeSlotList.add(new TimeSlot("15:25", "15:55"));
        expectedTimeSlotList.add(new TimeSlot("15:30", "16:00"));
        expectedTimeSlotList.add(new TimeSlot("18:00", "18:30"));

        //When
        when(availableMeetingService.findAvailableMeetingTerms(calendar1, calendar2, meetingDurationMinutes)).thenReturn(expectedTimeSlotList);
        //Then
        assertEquals(expectedTimeSlotList, calendarService.findAvailableMeetingTimeSlots(calendar1, calendar2, meetingDuration));
        verify(availableMeetingService).findAvailableMeetingTerms(calendar1, calendar2, meetingDurationMinutes);

    }


    @Test
    @DisplayName("Should Return Calendar1 When Invoked")
    public void shouldReturnCalendar1WhenInvoked() throws IOException {

        //Given
        Calendar calendar1 = new Calendar();
        calendar1.setWorkingHours(new WorkingHours("10:00", "18:30"));
        List<Meeting> meetings1 = new ArrayList<>();
        meetings1.add(new Meeting("10:00", "11:30"));
        meetings1.add(new Meeting("12:30", "14:30"));
        meetings1.add(new Meeting("14:30", "15:00"));
        meetings1.add(new Meeting("16:00", "17:00"));
        calendar1.setPlanedMeetings(meetings1);

        //When
        when(fileService.readCalendarFromJsonFile(CALENDAR1_PATH)).thenReturn(calendar1);
        //Then
        assertEquals(calendar1, calendarService.readCalendar1());
        verify(fileService).readCalendarFromJsonFile(CALENDAR1_PATH);
    }

    @Test
    @DisplayName("Should Return Calendar2 When Invoked")
    public void shouldReturnCalendar2WhenInvoked() throws IOException {

        //Given
        Calendar calendar2 = new Calendar();
        calendar2.setWorkingHours(new WorkingHours("09:00", "19:55"));
        List<Meeting> meetigns2 = new ArrayList<>();
        meetigns2.add(new Meeting("08:00", "09:20"));
        meetigns2.add(new Meeting("09:00", "10:30"));
        meetigns2.add(new Meeting("12:00", "13:00"));
        meetigns2.add(new Meeting("16:00", "18:00"));
        calendar2.setPlanedMeetings(meetigns2);

        //When
        when(fileService.readCalendarFromJsonFile(CALENDAR2_PATH)).thenReturn(calendar2);
        //Then
        assertEquals(calendar2, calendarService.readCalendar2());
        verify(fileService).readCalendarFromJsonFile(CALENDAR2_PATH);
    }

}