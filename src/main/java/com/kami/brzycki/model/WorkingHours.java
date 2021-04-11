package com.kami.brzycki.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class WorkingHours implements Comparable {

    private TimeSlot timeSlot;

    public WorkingHours(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public WorkingHours(LocalTime start, LocalTime end) {
        this.timeSlot = new TimeSlot(start, end);
    }

    public WorkingHours(String start, String end) {
        this.timeSlot = new TimeSlot(start, end);
    }

    public LocalTime getStart() {
        return timeSlot.getStart();
    }

    public LocalTime getEnd() {
        return timeSlot.getEnd();
    }

    @Override
    public int compareTo(Object workHours) {
        WorkingHours compareWorkHours = (WorkingHours) workHours;
        return this.timeSlot.compareTo(compareWorkHours.getTimeSlot());
    }


}
