package com.kami.brzycki.service;

import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.Meeting;
import com.kami.brzycki.model.TimeSlot;
import com.kami.brzycki.model.WorkingHours;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class AvailableMeetingServiceTest {
    static AvailableMeetingService availableMeetingService = null;

    @BeforeAll
    public static void setUp() {
        availableMeetingService = new AvailableMeetingService();

    }

    @Test
    @DisplayName("Should Throw Runtime Exception When One Of Calendar Have Not Set Work Hours")
    public void shouldThrowRuntimeExceptionWhenWorkHoursAreNull() {

        int meetingDuration = 30;
        Calendar calendar1 = new Calendar();
        Calendar calendar2 = new Calendar();

        Assertions.assertThrows(RuntimeException.class, () -> {
            availableMeetingService.findAvailableMeetingTerms(calendar1, calendar2, meetingDuration);
        });
    }

    @Test
    @DisplayName("Should Throw Illegal Argument Exception When Meeting Duration Is Equal To Zero")
    public void shouldThrowIllegalArgumentExceptionWhenMeetingDurationIsZero() {

        int meetingDuration = 0;
        Calendar calendar1 = new Calendar();
        calendar1.setWorkingHours(new WorkingHours("09:00", "17:00"));
        Calendar calendar2 = new Calendar();
        calendar2.setWorkingHours(new WorkingHours("07:00", "15:00"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            availableMeetingService.findAvailableMeetingTerms(calendar1, calendar2, meetingDuration);
        });
    }

    @Test
    @DisplayName("Should Return Empty List When Non Meeting Time Slot Is Available ")
    public void shouldReturnEmptyListWhenNoMeetingTimeSlotFound() throws Exception {

        int meetingDuration = 30;
        Calendar calendar1 = new Calendar();
        calendar1.setWorkingHours(new WorkingHours("08:00", "13:00"));
        Calendar calendar2 = new Calendar();
        calendar2.setWorkingHours(new WorkingHours("14:00", "18:00"));

        List<TimeSlot> meetingSlots = availableMeetingService.findAvailableMeetingTerms(calendar1, calendar2, meetingDuration);
        Assertions.assertEquals(Collections.emptyList(), meetingSlots);
    }

    @Test
    @DisplayName("Should Return Non Empty list When Data Is Correct And There Are Possible Time Slots")
    public void shouldReturnNonEmptyListWhenCorrectData() throws Exception {

        int meetingDuration = 30;
        Calendar calendar1 = new Calendar();
        calendar1.setWorkingHours(new WorkingHours("10:00", "18:00"));
        List<Meeting> meetigns1 = new ArrayList<>();
        meetigns1.add(new Meeting("10:00", "11:30"));
        meetigns1.add(new Meeting("13:00", "15:00"));
        calendar1.setPlanedMeetings(meetigns1);

        Calendar calendar2 = new Calendar();
        calendar2.setWorkingHours(new WorkingHours("14:00", "22:00"));
        List<Meeting> meetigns2 = new ArrayList<>();
        meetigns2.add(new Meeting("14:00", "15:00"));
        meetigns2.add(new Meeting("19:00", "20:00"));
        calendar2.setPlanedMeetings(meetigns2);

        List<TimeSlot> result = availableMeetingService.findAvailableMeetingTerms(calendar1, calendar2, meetingDuration);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("Should Return Non Empty List When There Are No Meetings in calendars And There Are Possible Meeting Time Slots")
    public void shouldReturnNonEmptyListWhenNoMeetingsInCalendar() throws Exception {

        int meetingDuration = 30;
        Calendar calendar1 = new Calendar();
        calendar1.setWorkingHours(new WorkingHours("10:00", "18:00"));
        Calendar calendar2 = new Calendar();
        calendar2.setWorkingHours(new WorkingHours("14:00", "22:00"));

        List<TimeSlot> result = availableMeetingService.findAvailableMeetingTerms(calendar1, calendar2, meetingDuration);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("Should Return Non Empty List When Only One Calendar Contain Meetings And There Are Possible Meeting Time Slots")
    public void shouldReturnNonEmptyListWhenOnlyOneCalendarContainMeetings() throws Exception {

        int meetingDuration = 30;
        Calendar calendar1 = new Calendar();
        calendar1.setWorkingHours(new WorkingHours("10:00", "18:00"));
        List<Meeting> meetigns = Arrays.asList(new Meeting("10:00", "11:30"), new Meeting("13:00", "15:00"));
        calendar1.setPlanedMeetings(meetigns);
        Calendar calendar2 = new Calendar();
        calendar2.setWorkingHours(new WorkingHours("14:00", "22:00"));

        List<TimeSlot> result = availableMeetingService.findAvailableMeetingTerms(calendar1, calendar2, meetingDuration);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("Should Return Correct Available Time Slots When Correct Data. Case From Task Description")
    public void shouldReturnAvailableTimeSlotsWhenCorrectData() throws Exception {

        int meetingDuration = 30;
        Calendar calendar1 = createCalendar1();
        Calendar calendar2 = createCalendar2();
        List<TimeSlot> expectedResultMeetingList = createResultCalendar();

        List<TimeSlot> actualResultMeetingList = availableMeetingService.findAvailableMeetingTerms(calendar1, calendar2, meetingDuration);
        Assertions.assertEquals(expectedResultMeetingList, actualResultMeetingList);

    }

    private Calendar createCalendar1() {
        Calendar calendar1 = new Calendar();
        calendar1.setWorkingHours(new WorkingHours("10:00", "18:30"));
        List<Meeting> meetigns1 = new ArrayList<>();
        meetigns1.add(new Meeting("10:00", "11:30"));
        meetigns1.add(new Meeting("12:30", "14:30"));
        meetigns1.add(new Meeting("14:30", "15:00"));
        meetigns1.add(new Meeting("16:00", "17:00"));
        calendar1.setPlanedMeetings(meetigns1);
        return calendar1;
    }

    private Calendar createCalendar2() {
        Calendar calendar2 = new Calendar();
        calendar2.setWorkingHours(new WorkingHours("09:00", "19:55"));
        List<Meeting> meetigns2 = new ArrayList<>();
        meetigns2.add(new Meeting("08:00", "09:20"));
        meetigns2.add(new Meeting("09:00", "10:30"));
        meetigns2.add(new Meeting("12:00", "13:00"));
        meetigns2.add(new Meeting("16:00", "18:00"));
        calendar2.setPlanedMeetings(meetigns2);
        return calendar2;
    }

    private List<TimeSlot> createResultCalendar() {

        List<TimeSlot> result = new ArrayList<>();
        result.add(new TimeSlot("11:30", "12:00"));
        result.add(new TimeSlot("15:00", "15:30"));
        result.add(new TimeSlot("15:05", "15:35"));
        result.add(new TimeSlot("15:10", "15:40"));
        result.add(new TimeSlot("15:15", "15:45"));
        result.add(new TimeSlot("15:20", "15:50"));
        result.add(new TimeSlot("15:25", "15:55"));
        result.add(new TimeSlot("15:30", "16:00"));
        result.add(new TimeSlot("18:00", "18:30"));
        return result;
    }


}