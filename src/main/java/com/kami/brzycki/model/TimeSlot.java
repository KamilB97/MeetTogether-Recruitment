package com.kami.brzycki.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot implements Comparable {


    private LocalTime start;
    private LocalTime end;

    public TimeSlot(String start, String end) {
        setStart(start);
        setEnd(end);
    }

    private void setStart(String start) {
        this.start = LocalTime.parse(start);
    }

    private void setEnd(String end) {
        this.end = LocalTime.parse(end);
    }

    public int getDuration() {
        int hours = end.getHour() - start.getHour();
        int minutes = end.getMinute() - start.getMinute();

        int durationInMinutes = hours * 60 + minutes;
        LocalTime duration = LocalTime.of(durationInMinutes / 60, durationInMinutes % 60);
        return durationInMinutes;
    }

    @Override
    public int compareTo(Object timeRange) {

        LocalTime comparingStart = ((TimeSlot) timeRange).getStart();

        int compareResult;
        compareResult = this.getStart().compareTo(comparingStart);

        if (compareResult == 0) {
            LocalTime comparingEnd = ((TimeSlot) timeRange).getEnd();
            compareResult = this.getEnd().compareTo(comparingEnd);
        }
        return compareResult;
    }
}
