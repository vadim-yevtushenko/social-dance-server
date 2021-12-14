package com.example.socialdanceserver.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateTimeUtils {

    public static Date fromLocalDateTimeToDate(LocalDateTime localDateTime){
        if (localDateTime == null){
            return null;
        }
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }
}
