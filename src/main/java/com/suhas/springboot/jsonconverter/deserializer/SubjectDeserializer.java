package com.suhas.springboot.jsonconverter.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suhas.springboot.domain.Subject;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SubjectDeserializer extends JsonDeserializer<Map<Integer, Subject>> {

    @Override
    public Map<Integer, Subject> deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Integer, Subject>  subjectsMap = new HashMap<>();

        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            while(jsonParser.nextToken() != JsonToken.END_ARRAY) {
                String subjectString = jsonParser.getValueAsString();
                //subjectString = subjectString.replaceAll("\"","\\\"");
                Subject subject = objectMapper.readValue(subjectString, Subject.class);
                subjectsMap.put(subject.getSubject().getSubjectid(), subject);
            }

            return subjectsMap;
        }

        return subjectsMap;
    }
}
