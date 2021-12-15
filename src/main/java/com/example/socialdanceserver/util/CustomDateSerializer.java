package com.example.socialdanceserver.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

import static com.example.socialdanceserver.util.DateTimeUtils.fromLocalDateTimeToDate;

@JsonComponent
public class CustomDateSerializer {

    public static class DateDeserializer extends JsonDeserializer<Date> {

        public final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            String date = null;
            try {
                date = jsonParser.getText();
                return dateTimeFormat.parse(date);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
