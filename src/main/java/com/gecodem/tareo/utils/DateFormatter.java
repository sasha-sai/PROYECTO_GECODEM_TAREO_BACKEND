package com.gecodem.tareo.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateFormatter {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    // =========================
    // STRING → LOCALDATE
    // =========================
    public static LocalDate stringToLocalDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Error parsing date: " + date, e);
        }
    }

    // =========================
    // LOCALDATE → STRING
    // =========================
    public static String localDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);
        return date.format(formatter);
    }

    // =========================
    // STRING → LOCALDATETIME
    // =========================
    public static LocalDateTime stringToLocalDateTime(String dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN);
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Error parsing datetime: " + dateTime, e);
        }
    }

    // =========================
    // LOCALDATETIME → STRING
    // =========================
    public static String localDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN);
        return dateTime.format(formatter);
    }

    // =========================
    // DATE → STRING
    // =========================
    public static String dateToString(Date date) {
        return localDateTimeToString(
                date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        );
    }

    // =========================
    // STRING → DATE
    // =========================
    public static Date stringToDate(String dateTime) {
        LocalDateTime localDateTime = stringToLocalDateTime(dateTime);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
