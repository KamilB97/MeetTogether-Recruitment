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
        boolean isAdded = false;

        if (!meetingConflictOccurs(meeting)) {
            planedMeetings.add(meeting);
            isAdded = true;
        } else {
            throw new RuntimeException("Meeting collision");
        }
    }

    private boolean meetingConflictOccurs(Meeting meeting) {

        long collisions = planedMeetings.stream().filter(currentMeeting -> {
            boolean isCollision = false;

            if ((currentMeeting.getStart().compareTo(meeting.getEnd()) < 0 && currentMeeting.getStart().compareTo(meeting.getStart()) >= 0)
                    || currentMeeting.getEnd().compareTo(meeting.getStart()) > 0 && currentMeeting.getEnd().compareTo(meeting.getEnd()) <= 0) {

                isCollision = true;
            }

            return isCollision;
        }).count();

        return collisions > 0 ? true : false;
    }


}
