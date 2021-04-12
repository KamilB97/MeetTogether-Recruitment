package com.kami.brzycki.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class WorkingHoursTest {


    @Test
    @DisplayName("Should Return Ascending Sorted Work Hours List When Sorted ")
    public void shouldReturnAscendingSortedWorkHoursWhenSort() {
        List<WorkingHours> expected = getCorrectlySortedWorkingHoursList();
        List<WorkingHours> actual = getWorkingHoursList().stream().sorted().collect(Collectors.toList());

        Assertions.assertEquals(expected, actual);
    }

    private List<WorkingHours> getWorkingHoursList() {
        List<WorkingHours> workingHours = new ArrayList<>();
        workingHours.add(new WorkingHours("06:01", "13:00"));
        workingHours.add(new WorkingHours("20:00", "22:20"));
        workingHours.add(new WorkingHours("06:00", "10:30"));
        workingHours.add(new WorkingHours("12:00", "23:00"));
        workingHours.add(new WorkingHours("16:00", "18:00"));
        workingHours.add(new WorkingHours("06:00", "13:00"));
        workingHours.add(new WorkingHours("18:00", "18:01"));
        workingHours.add(new WorkingHours("05:59", "13:00"));

        return workingHours;
    }


    private List<WorkingHours> getCorrectlySortedWorkingHoursList() {
        List<WorkingHours> sortedWorkingHours = new ArrayList<>();
        sortedWorkingHours.add(new WorkingHours("05:59", "13:00"));
        sortedWorkingHours.add(new WorkingHours("06:00", "10:30"));
        sortedWorkingHours.add(new WorkingHours("06:00", "13:00"));
        sortedWorkingHours.add(new WorkingHours("06:01", "13:00"));
        sortedWorkingHours.add(new WorkingHours("12:00", "23:00"));
        sortedWorkingHours.add(new WorkingHours("16:00", "18:00"));
        sortedWorkingHours.add(new WorkingHours("18:00", "18:01"));
        sortedWorkingHours.add(new WorkingHours("20:00", "22:20"));

        return sortedWorkingHours;
    }
}