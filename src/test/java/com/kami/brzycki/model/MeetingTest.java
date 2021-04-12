package com.kami.brzycki.model;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MeetingTest {

    @Test
    @DisplayName("Should Add Meeting")
    public void shouldAddMeeting() {
        Calendar calendar = new Calendar();
        calendar.addMeeting(new Meeting("09:30", "11:00"));
        calendar.addMeeting(new Meeting("12:00", "13:00"));
        int numberOfMeetings = calendar.getPlanedMeetings().size();

        Assertions.assertEquals(2, numberOfMeetings);
    }

    @Test
    @DisplayName("Should Return Ascending Sorted Meeting List When Sorted ")
    public void shouldReturnAscendingSortedMeetingsWhenSorted() {
        List<Meeting> expected = getCorrectSortedMeetingList();
        List<Meeting> actual = getMeetingList().stream().sorted().collect(Collectors.toList());

        Assertions.assertEquals(expected, actual);
    }

    private List<Meeting> getMeetingList() {
        List<Meeting> meetigns = new ArrayList<>();
        meetigns.add(new Meeting("06:01", "13:00"));
        meetigns.add(new Meeting("20:00", "22:20"));
        meetigns.add(new Meeting("06:00", "10:30"));
        meetigns.add(new Meeting("12:00", "23:00"));
        meetigns.add(new Meeting("16:00", "18:00"));
        meetigns.add(new Meeting("06:00", "13:00"));
        meetigns.add(new Meeting("18:00", "18:01"));
        meetigns.add(new Meeting("05:59", "13:00"));

        return meetigns;
    }

    private List<Meeting> getCorrectSortedMeetingList() {
        List<Meeting> sortedMeetings = new ArrayList<>();
        sortedMeetings.add(new Meeting("05:59", "13:00"));
        sortedMeetings.add(new Meeting("06:00", "10:30"));
        sortedMeetings.add(new Meeting("06:00", "13:00"));
        sortedMeetings.add(new Meeting("06:01", "13:00"));
        sortedMeetings.add(new Meeting("12:00", "23:00"));
        sortedMeetings.add(new Meeting("16:00", "18:00"));
        sortedMeetings.add(new Meeting("18:00", "18:01"));
        sortedMeetings.add(new Meeting("20:00", "22:20"));

        return sortedMeetings;
    }
}