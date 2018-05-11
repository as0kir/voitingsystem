package ru.askir.voitingsystem.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtil {

    private static LocalTime beginTime = LocalTime.of(00, 00);
    private static LocalTime endTime = LocalTime.of(11, 00);
    private static LocalDateTime mockDate;

    public static LocalDateTime getMockDate() {
        return mockDate;
    }

    public static void setMockDate(LocalDateTime mockDate) {
        DateUtil.mockDate = mockDate;
    }

    public static boolean enableVoite(LocalDate checkedDate) {
        return checkedDate.equals(getCurrentDate()) && getCurrentTime().isAfter(beginTime) && getCurrentTime().isBefore(endTime);
    }

    public static LocalDate getCurrentDate() {
        if(mockDate == null)
            return LocalDate.now();
        else
            return mockDate.toLocalDate();
    }

    public static LocalDateTime getCurrentDateTime() {
        if(mockDate == null)
            return LocalDateTime.now();
        else
            return mockDate;
    }

    public static LocalTime getCurrentTime() {
        if(mockDate == null)
            return LocalTime.now();
        else
            return mockDate.toLocalTime();
    }
}
