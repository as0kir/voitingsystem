package ru.askir.voitingsystem.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private LocalTime beginTime;
    private LocalTime endTime;
    private LocalDateTime mockDate;

    public DateTimeUtil(LocalTime beginTime, LocalTime endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public LocalDateTime getMockDate() {
        return mockDate;
    }

    public void setMockDate(LocalDateTime mockDate) {
        this.mockDate = mockDate;
    }

    public boolean enableVoite(LocalDate checkedDate) {
        return checkedDate.equals(getCurrentDate()) && getCurrentTime().compareTo(beginTime) >= 0 && getCurrentTime().compareTo(endTime) <= 0;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getCurrentDate() {
        if(mockDate == null)
            return LocalDate.now();
        else
            return mockDate.toLocalDate();
    }

    public LocalDateTime getCurrentDateTime() {
        if(this.mockDate == null)
            return LocalDateTime.now();
        else
            return this.mockDate;
    }

    public LocalTime getCurrentTime() {
        if(mockDate == null)
            return LocalTime.now();
        else
            return mockDate.toLocalTime();
    }

    public String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static LocalTime parseLocalTime(String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }
}
