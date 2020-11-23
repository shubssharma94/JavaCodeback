package com.suhas.springboot.jsonconverter.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimestampDeserializer extends JsonDeserializer<Date> {

    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sm.SSS'Z'");
    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampDeserializer.class);

    {
        dateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public Date deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException {
        Date result = null;
        String inputDate = null;
        try {
            inputDate = jsonParser.getText().trim();
            result = dateFormat1.parse(inputDate);
        } catch (ParseException exception) {
            LOGGER.error(String.format("Error while deserializing the input date : %s", inputDate), exception);
        }

        return result;
    }
}
