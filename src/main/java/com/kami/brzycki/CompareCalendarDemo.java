package com.kami.brzycki;

import com.kami.brzycki.model.Calendar;
import com.kami.brzycki.model.TimeSlot;
import com.kami.brzycki.service.CalendarService;
import com.kami.brzycki.service.MappingService;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompareCalendarDemo {

    public static void main(String[] args) {

        CalendarService calendarService = new CalendarService();
        MappingService mappingService = new MappingService();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInsert meeting duration in format hh:mm");
        String duration = "00:30";
        boolean isInputValid = false;
        do {

            duration = scanner.nextLine();
            isInputValid = validateConsoleInput(duration);

            if (!isInputValid) {
                System.out.println("Insert meeting duration in the hh:mm format");
            }

        } while (!isInputValid);

        LocalTime meetingDuration = LocalTime.parse(duration);
        try {
            Calendar calendar1 = calendarService.readCalendar1();
            Calendar calendar2 = calendarService.readCalendar2();

            List<TimeSlot> availableMeetingTimeSlots = calendarService.findAvailableMeetingTimeSlots(calendar1, calendar2, meetingDuration);
            String jsonArrayAvailableMeetings = mappingService.serializeAvailableMeetingTimeSlotsToJson(availableMeetingTimeSlots);
            System.out.println("\n" + "Output: " + jsonArrayAvailableMeetings);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static boolean validateConsoleInput(String input) {

        boolean isMatchingPattern = false;
        Pattern pattern = Pattern.compile("\\d{2}:\\d{2}");
        Matcher matcher = pattern.matcher(input);
        isMatchingPattern = matcher.matches();

        if (isMatchingPattern) {
            try {
                LocalTime localTime = LocalTime.parse(input);
            } catch (DateTimeParseException e) {
                isMatchingPattern = false;
                System.out.println("Time must be less than 24:00");
            }
        }
        return isMatchingPattern;
    }
}
