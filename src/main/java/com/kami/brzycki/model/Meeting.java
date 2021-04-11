package com.kami.brzycki.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Meeting implements Comparable {

    private TimeSlot timeSlot;


    public Meeting(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Meeting(LocalTime start, LocalTime end) {
        this.timeSlot = new TimeSlot(start, end);
    }

    public Meeting(String start, String end) {
        this.timeSlot = new TimeSlot(start, end);
    }

    public LocalTime getStart() {
        return timeSlot.getStart();
    }

    public LocalTime getEnd() {
        return timeSlot.getEnd();
    }

    @Override
    public int compareTo(Object meeting) {
        Meeting compareMeeting = (Meeting) meeting;
        return this.timeSlot.compareTo(compareMeeting.getTimeSlot());
    }


}
