package com.kami.brzycki.algorithm;

import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.Meeting;
import com.kami.brzycki.model.TimeSlot;
import com.kami.brzycki.model.WorkingHours;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MeetTogetherAlgorithm {

    private static final int TIME_INTERVAL_BETWEEN_PROPOSED_MEETINGS = 5;

    public List<TimeSlot> findAvailableMeetingTerms(Calendar calendar1, Calendar calendar2, int meetingDuration) throws Exception {

        if (calendar1.getWorkingHours() == null || calendar2.getWorkingHours() == null) {
            throw new RuntimeException("No work hours specified");
        }

        if (meetingDuration <= 0) {
            throw new IllegalArgumentException("meeting duration equal to 0");
        }

        TimeSlot commonWorkHours = getCommonWorkHours(calendar1.getWorkingHours(), calendar2.getWorkingHours());
        if (commonWorkHours == null) {
            return Collections.emptyList();
        }

        List<Meeting> allMeetings = combineMeetings(calendar1.getPlanedMeetings(), calendar2.getPlanedMeetings());
        allMeetings = allMeetings.stream()
                .filter(isMeetingWithinCommonWorkHours(commonWorkHours))
                .sorted()
                .collect(Collectors.toList());

        List<TimeSlot> availableMeetingTimeSlots = getAvailableMeetingTimeSlots(allMeetings, commonWorkHours, meetingDuration);

        return availableMeetingTimeSlots;
    }

    private List<Meeting> combineMeetings(List<Meeting> meetings1, List<Meeting> meetings2) {
        List<Meeting> allCurrentMeetings = meetings1;
        allCurrentMeetings.addAll(meetings2);
        return allCurrentMeetings;
    }

    private TimeSlot getCommonWorkHours(WorkingHours person1, WorkingHours person2) {

        if (person1.getStart().compareTo(person2.getEnd()) >= 0 || person2.getStart().compareTo(person1.getEnd()) >= 0) {
            return null;
        }

        boolean isPerson1WorkStartTimeGreater = false;
        boolean isPerson1WorkEndTimeGreater = false;

        if (person1.getStart().compareTo(person2.getStart()) >= 0) {
            isPerson1WorkStartTimeGreater = true;
        }
        if (person1.getEnd().compareTo(person2.getEnd()) <= 0) {
            isPerson1WorkEndTimeGreater = true;
        }

        LocalTime start = isPerson1WorkStartTimeGreater ? person1.getStart() : person2.getStart();
        LocalTime end = isPerson1WorkEndTimeGreater ? person1.getEnd() : person2.getEnd();

        return new TimeSlot(start, end);
    }

    private List<TimeSlot> getAvailableMeetingTimeSlots(List<Meeting> allMeetings, TimeSlot commonWorkHours, int meetingDuration) {

        List<TimeSlot> meetingTimeSlotCandidates = new ArrayList<>();

        if (allMeetings.size() != 0) {
            for (int currentMeetingIndex = 0; currentMeetingIndex < allMeetings.size(); currentMeetingIndex++) {

                LocalTime thisMeetingEndTime = allMeetings.get(currentMeetingIndex).getEnd();
                TimeSlot meetingTimeSlotCandidate = null;

                int nextMeetingIndex = currentMeetingIndex + 1;
                if (nextMeetingIndex < allMeetings.size()) {

                    LocalTime nextMeetingStartTime = allMeetings.get(nextMeetingIndex).getStart();
                    if (nextMeetingStartTime.compareTo(thisMeetingEndTime) > 0) {
                        meetingTimeSlotCandidate = new TimeSlot(thisMeetingEndTime, nextMeetingStartTime);
                    }

                } else if (nextMeetingIndex == allMeetings.size()) {

                    LocalTime workHoursEndTime = commonWorkHours.getEnd();
                    if (thisMeetingEndTime.compareTo(workHoursEndTime) < 0) {
                        meetingTimeSlotCandidate = new TimeSlot(thisMeetingEndTime, workHoursEndTime);
                    }
                }

                if (meetingTimeSlotCandidate != null && meetingTimeSlotCandidate.getDuration() >= meetingDuration) {
                    meetingTimeSlotCandidates.add(meetingTimeSlotCandidate);
                }
            }
        } else {
            // case when both calendars have no meetings yet
            meetingTimeSlotCandidates.add(commonWorkHours);
        }


        List<TimeSlot> availableMeetingTimeSlots = new ArrayList<>();

        for (TimeSlot timeSlot : meetingTimeSlotCandidates) {
            availableMeetingTimeSlots.addAll(getPossibleMeetingTimeSlots(timeSlot, meetingDuration));
        }

        return availableMeetingTimeSlots;
    }

    private List<TimeSlot> getPossibleMeetingTimeSlots(TimeSlot timeSlot, long meetingDuration) {
        List<TimeSlot> possibleMeetingTimeSlots = new ArrayList<>();

        LocalTime startTime = timeSlot.getStart();
        LocalTime endTime = timeSlot.getEnd();

        do {
            possibleMeetingTimeSlots.add(new TimeSlot(startTime, startTime.plusMinutes(meetingDuration)));
            startTime = startTime.plusMinutes(TIME_INTERVAL_BETWEEN_PROPOSED_MEETINGS);
        } while (startTime.plusMinutes(meetingDuration).compareTo(endTime) <= 0);

        return possibleMeetingTimeSlots;
    }

    private Predicate<Meeting> isMeetingWithinCommonWorkHours(TimeSlot commonWorkHours) {

        return meeting -> {
            boolean isInCommonHours = false;

            if (meeting.getStart().compareTo(commonWorkHours.getStart()) >= 0 && meeting.getEnd().compareTo(commonWorkHours.getStart()) >= 0) {
                isInCommonHours = true;
            }
            return isInCommonHours;
        };
    }
}
