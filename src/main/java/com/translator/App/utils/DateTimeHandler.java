package com.translator.App.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeHandler {

    public static String timeNow(){
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Format the current date and time using a specific pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        return currentDateTime.format(formatter);
    }

    public static void timeAfter30Day(){
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime newDate = date.plusDays(30);

    }
}
