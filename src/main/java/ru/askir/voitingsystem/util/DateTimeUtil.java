package ru.askir.voitingsystem.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private static LocalTime beginTime = LocalTime.of(00, 00);
    private static LocalTime endTime = LocalTime.of(11, 00);
    private static LocalDateTime mockDate;

    public static LocalDateTime getMockDate() {
        return mockDate;
    }

    public static void setMockDate(LocalDateTime mockDate) {
        DateTimeUtil.mockDate = mockDate;
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

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static LocalTime parseLocalTime(String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }
}
