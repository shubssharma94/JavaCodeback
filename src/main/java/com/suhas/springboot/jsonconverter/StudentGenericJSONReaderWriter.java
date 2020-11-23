package com.suhas.springboot.jsonconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.suhas.springboot.domain.JSONDomain;
import com.suhas.springboot.domain.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentGenericJSONReaderWriter<T> {

    private  ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentGenericJSONReaderWriter.class);

    public <T extends JSONDomain<T>> T convertJSONToObject(
            final String json,
            final Class<T> jsonDomainClassType)  {
        try {
            return objectMapper.readValue(json, jsonDomainClassType);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting JSON to Object", e);
        }

        return null;
    }

    public String convertObjectToJSON(
            final T jsonDomainClassInstance)  {
        try {
            String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonDomainClassInstance);
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return jsonInString;


        } catch (JsonProcessingException e) {
            LOGGER.error("Error while converting Object to JSON", e);
        }
        return null;
    }

    public String flattenTheJSON(
            final String json)  {
        String flattenedJson = JsonFlattener.flatten(json);
        return flattenedJson;
    }

    public String unflattenTheJSON(
            final String flattenedJson)  {
        String nestedJson = JsonUnflattener.unflatten(flattenedJson);
        return nestedJson;
    }

    public static void main(String[] args) {
        String jsonString = getJSONString();
        LOGGER.info("JSON  ::" + jsonString);

        StudentGenericJSONReaderWriter<Student> readerWriter = new StudentGenericJSONReaderWriter<>();
        Student student = readerWriter.convertJSONToObject(jsonString, Student.class);

        LOGGER.info("Roll Number ::" + student.getRollnumber());
        LOGGER.info("Name ::" + student.getName());
        LOGGER.info("Subjects ::" + student.getSubjects());
    }
    
    private static String getJSONString() {
       return "{\n" +
               "  \"rollnumber\": 123,\n" +
               "  \"name\": \"Suhas Naik\",\n" +
               "  \"age\": 32,\n" +
               "  \"subjects\": [\n" +
               "    \"{\\\"subject\\\":{\\\"subjectid\\\":1, \\\"subjectname\\\":\\\"Hindi\\\"}}\",\n" +
               "    \"{\\\"subject\\\":{\\\"subjectid\\\":2, \\\"subjectname\\\":\\\"English\\\"}}\",\n" +
               "    \"{\\\"subject\\\":{\\\"subjectid\\\":3, \\\"subjectname\\\":\\\"Unrdu\\\"}}\"\n" +
               "  ]\n" +
               "}";
    }
}
