package com.suhas.springboot.jsonconverter.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimestampSerializer extends JsonSerializer<Date> {

    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sm.SSS'Z'");
    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampSerializer.class);

    {
        dateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public void serialize(
            Date date,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
        String outputDateString = dateFormat1.format(date);
        jsonGenerator.writeString(outputDateString);
    }
}
