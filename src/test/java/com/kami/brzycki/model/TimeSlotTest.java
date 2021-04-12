package com.kami.brzycki.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {


    @Test
    public void shouldPassWhenReturnCorrectTimeSlotDuration() {

        TimeSlot timeSlot = new TimeSlot("10:30", "13:00");
        int duration = 150;

        Assertions.assertEquals(duration, timeSlot.getDuration());
    }

    @Test
    @DisplayName("Should Return Ascending Sorted TimeSlot List When Sorted ")
    public void shouldReturnAscendingSortedTimeSlotWhenSort() {

        List<TimeSlot> expectedSortedTimeSlots = getCorrectlySortedTimeSlotList();

        List<TimeSlot> actualSortedTimeSlots = getTimeSlotList().stream().sorted().collect(Collectors.toList());
        Assertions.assertEquals(expectedSortedTimeSlots, actualSortedTimeSlots);
    }

    private List<TimeSlot> getTimeSlotList() {
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(new TimeSlot("06:01", "13:00"));
        timeSlots.add(new TimeSlot("20:00", "22:20"));
        timeSlots.add(new TimeSlot("06:00", "10:30"));
        timeSlots.add(new TimeSlot("12:00", "23:00"));
        timeSlots.add(new TimeSlot("16:00", "18:00"));
        timeSlots.add(new TimeSlot("06:00", "13:00"));
        timeSlots.add(new TimeSlot("18:00", "18:01"));
        timeSlots.add(new TimeSlot("05:59", "13:00"));

        return timeSlots;
    }


    private List<TimeSlot> getCorrectlySortedTimeSlotList() {
        List<TimeSlot> sortedTimeSlots = new ArrayList<>();
        sortedTimeSlots.add(new TimeSlot("05:59", "13:00"));
        sortedTimeSlots.add(new TimeSlot("06:00", "10:30"));
        sortedTimeSlots.add(new TimeSlot("06:00", "13:00"));
        sortedTimeSlots.add(new TimeSlot("06:01", "13:00"));
        sortedTimeSlots.add(new TimeSlot("12:00", "23:00"));
        sortedTimeSlots.add(new TimeSlot("16:00", "18:00"));
        sortedTimeSlots.add(new TimeSlot("18:00", "18:01"));
        sortedTimeSlots.add(new TimeSlot("20:00", "22:20"));

        return sortedTimeSlots;
    }


}