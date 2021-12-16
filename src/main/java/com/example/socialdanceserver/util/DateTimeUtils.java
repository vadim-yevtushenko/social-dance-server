package com.example.socialdanceserver.util;

import java.time.*;
import java.util.Date;

public class DateTimeUtils {

    public static Date fromLocalDateTimeToDate(LocalDateTime localDateTime){
        if (localDateTime == null){
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static Date fromLocalDateToDate(LocalDate localDate){
        if (localDate == null){
            return null;
        }
        return Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static LocalDateTime fromDateToLocalDateTime(Date date){
        if (date == null){
            return null;
        }
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDate fromDateToLocalDate(Date date){
        if (date == null){
            return null;
        }
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


}
