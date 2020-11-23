package com.suhas.springboot.flattern;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.suhas.springboot.domain.JSONDomain;
import com.suhas.springboot.domain.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;


import java.util.*;

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


    public String flattenTheJSON(
            final String json)  {
        String flattenedJson = JsonFlattener.flatten(json);
        return flattenedJson;
    }

    public Map<String, Object> flattenTheJSONAsMap(
            final String json)  {
        Map<String, Object> flattenedJsonAsMap = JsonFlattener.flattenAsMap(json);
        return flattenedJsonAsMap;
    }

    public String unflattenTheJSON(
            final String flattenedJson)  {
        String nestedJson = JsonUnflattener.unflatten(flattenedJson);
        return nestedJson;
    }

    public static void main(String[] args) throws Exception{
        List<Map<String, Object>> studentDetails = usingFlattenMap();
        LOGGER.info("JSON As List of Map   ::" + studentDetails);
    }

    private static List<Map<String, Object>> filteringTheMap(
            String filteringJSON,
            List<Map<String, Object>> studentDetails ) {
        StudentGenericJSONReaderWriter<Student> readerWriter = new StudentGenericJSONReaderWriter<>();
        FilteringCriteria filteringCriteria = readerWriter.convertJSONToObject(filteringJSON, FilteringCriteria.class);




        return null;
    }

    private static List<Map<String, Object>> usingFlattenMap() {
        String jsonString = getJSONString();
        LOGGER.info("JSON  ::" + jsonString);

        StudentGenericJSONReaderWriter<Student> readerWriter = new StudentGenericJSONReaderWriter<>();
        Map<String, Object> flattenedJsonAsMap = readerWriter.flattenTheJSONAsMap(jsonString);

        List<Map<String, Object>> studentDetails = new ArrayList<>();

        Map<String, Object> mainStudentObjectMap = new LinkedHashMap<>();
        for(Map.Entry<String, Object> entry : flattenedJsonAsMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if(key.contains("subject")) {
                Map<String, Object> subjectFlattenedJsonAsMap = readerWriter.flattenTheJSONAsMap(value.toString());
                subjectFlattenedJsonAsMap.putAll(mainStudentObjectMap);
                studentDetails.add(subjectFlattenedJsonAsMap);
            } else {
                mainStudentObjectMap.put(key, value);
            }
        }
        return studentDetails;
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

    public static void loopThroughJson(
            Object input) throws JSONException {

        if (input instanceof JSONObject) {
            Iterator<?> keys = ((JSONObject) input).keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();

                if (!(((JSONObject) input).get(key) instanceof JSONArray)) {
                    if (((JSONObject) input).get(key) instanceof JSONObject) {
                        loopThroughJson(((JSONObject) input).get(key));
                    } else {
                        System.out.println(key + "=" + ((JSONObject) input).get(key));
                    }
                }else {
                    loopThroughJson(new JSONArray(((JSONObject) input).get(key).toString()));
                }
            }
        }

        if (input instanceof JSONArray) {
            List<Map<String, Object>> studentDetailsMap = new ArrayList<>();
            for (int i = 0; i < ((JSONArray) input).length(); i++) {
                String subjectStringObject = ((JSONArray) input).getString(i);
                JSONObject a = new JSONObject(subjectStringObject);
                loopThroughJson(a);
            }
        }
    }

    public static List<Map<String, Object>> getStudentDetails()throws Exception {
        String jsonString = getJSONString();
        LOGGER.info("JSON  ::" + jsonString);

        List<Map<String, Object>> studentDetails = new ArrayList<>();


        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //read JSON like DOM Parser
        JsonNode rootNode = objectMapper.readTree(jsonString);


        Iterator<JsonNode> elements = rootNode.elements();
        while(elements.hasNext()){
            JsonNode node = elements.next();

            Iterator<java.util.Map.Entry<String, JsonNode>> fields = node.fields();
            while(fields.hasNext()) {
                Map.Entry<String, JsonNode> elt=  fields.next();
                String fieldName = elt.getKey();
                System.out.println(String.format("Key : %s,Value = %s ", fieldName, elt.getKey()));
            }
        }

        return studentDetails;
    }

    private static class FilteringCriteria implements JSONDomain {
        private String value;
        private String operator;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }
    }

}
