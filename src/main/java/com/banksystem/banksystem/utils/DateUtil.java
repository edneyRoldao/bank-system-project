package com.banksystem.banksystem.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDate stringToLocalDate(String value, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(value, formatter);
    }

    public static Date localDateToDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    public static Timestamp localDateTimeToTimestamp(LocalDateTime localDateTime) {
        // https://mkyong.com/java8/java-8-convert-localdatetime-to-timestamp/
        return Timestamp.valueOf(localDateTime);
    }

}
