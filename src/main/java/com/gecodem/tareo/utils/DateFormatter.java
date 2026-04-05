package com.gecodem.tareo.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatter {

    private DateFormatter() {
    }

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    // 🔥 NUEVO: formato 12h con AM/PM
    private static final String TIME_AM_PM_PATTERN = "hh:mm a";

    // =========================
    // STRING → LOCALDATE
    // =========================
    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);
        return LocalDate.parse(date, formatter);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN);
        return LocalDateTime.parse(dateTime, formatter);
    }

    // =========================
    // LOCALDATETIME → STRING
    // =========================
    public static String localDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN);
        return dateTime.format(formatter);
    }

    // =========================
    // 🔥 LOCALDATETIME → SOLO HORA (AM/PM)
    // =========================
    public static String localDateTimeToHourAmPm(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_AM_PM_PATTERN);
        return dateTime.format(formatter);
    }

    // =========================
    // DATE → STRING
    // =========================
    public static String dateToString(Date date) {
        if (date == null) return null;
        return localDateTimeToString(
                date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        );
    }

    // =========================
    // 🔥 DATE → SOLO HORA (AM/PM)
    // =========================
    public static String dateToHourAmPm(Date date) {
        LocalDateTime localDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return localDateTimeToHourAmPm(localDateTime);
    }

    // =========================
    // STRING → DATE
    // =========================
    public static Date stringToDate(String dateTime) {
        LocalDateTime localDateTime = stringToLocalDateTime(dateTime);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}