package com.demo.masterslavesample.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeConverterUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_FORMATTER_DOWNLOAD = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER_REVERSE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_FORMATTER_TRIP_START_NOTIFICATION =
            DateTimeFormatter.ofPattern("hh:mm a 'on' dd MMMM yy");
    private static final DateTimeFormatter DAY_AND_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("EEEE, dd-MM-yyyy");


    public DateTimeConverterUtils() {
    }

    //takes date as "mm-dd-yyyy" and time as "HH:mm"
    public static Long convertDateTimeStringToEpoch(String date, String time) {
        if (time.split(":")[0].length() == 1)
            time = "0" + time;

        time += ":00";
        LocalDate localDate = LocalDate.parse(date, DATE_FORMATTER);

        String formattedDate = localDate.format(DATE_FORMATTER_REVERSE);

        String dateTimeString = formattedDate + " " + time;
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, DATETIME_FORMATTER);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("GMT+05:30"));
        return zonedDateTime.toInstant().toEpochMilli();
    }

    public static LocalDate convertDateStringToLocalDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);

    }

    public static Long convertDatetimeToEpoch(LocalDateTime dateTime) {
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.of("Asia/Kolkata"));
        return zonedDateTime.toInstant().toEpochMilli();
    }

    public static String getTripStartNotificationFormat(Long epochTime) {
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        Instant instant = Instant.ofEpochMilli(epochTime);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zoneId);
        return dateTime.format(DATE_FORMATTER_TRIP_START_NOTIFICATION);
    }

    public static LocalDateTime convertEpochToDatetime(Long epochTime) {
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        Instant instant = Instant.ofEpochSecond(epochTime / 1000);
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    public static String getDatefromLocalDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMATTER_DOWNLOAD);
    }

    public static String getDateFromLocalDateTimeForTripCreation(LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMATTER);
    }

    public static String getTimefromLocalDateTime(LocalDateTime dateTime) {
        return dateTime.format(TIME_FORMATTER);
    }

    public static String getDayAndDate(LocalDate date){
        return date.format(DAY_AND_DATE_FORMATTER);
    }

    public static String convertEpochToFormat(Long epochTime, DateTimeFormatter dateTimeFormat){
        return convertEpochToDatetime(epochTime).format(dateTimeFormat);
    }
}
