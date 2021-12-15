package com.example.socialdanceserver.util;

import java.time.*;
import java.util.Date;

public class DateTimeUtils {

    public static Date fromLocalDateTimeToDate(LocalDateTime localDateTime){
        if (localDateTime == null){
            return null;
        }
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

    public static LocalDateTime fromDateToLocalDateTime(Date date){
        if (date == null){
            return null;
        }
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }


}
