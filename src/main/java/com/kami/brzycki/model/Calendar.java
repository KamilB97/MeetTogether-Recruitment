package com.kami.brzycki.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Calendar {

    private WorkingHours workingHours;
    private List<Meeting> planedMeetings;

    public Calendar() {
        planedMeetings = new ArrayList<>();
    }

    public Calendar(WorkingHours workingHours, List<Meeting> planned_meeting) {
        this.workingHours = workingHours;
        this.planedMeetings = planned_meeting;
    }

    public void addMeeting(Meeting meeting) {

        planedMeetings.add(meeting);

    }
}
