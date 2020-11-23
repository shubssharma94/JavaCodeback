package com.suhas.springboot.jsonconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.suhas.springboot.domain.Employee;
import com.suhas.springboot.domain.JSONDomain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericJSONReaderWriter<T> {

    private  ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericJSONReaderWriter.class);

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
        GenericJSONReaderWriter<Employee> readerWriter = new GenericJSONReaderWriter<>();

        String flatternJSONString = readerWriter.flattenTheJSON(jsonString);
        System.out.println(flatternJSONString);
        Employee flattenedemployee = readerWriter.convertJSONToObject(flatternJSONString, Employee.class);

        System.out.println(flattenedemployee.getId());
        System.out.println(flattenedemployee.getName());

        System.out.println(flattenedemployee.getStreet());
        System.out.println(flattenedemployee.getCity());
        System.out.println(flattenedemployee.getZipcode());

        System.out.println(flattenedemployee.getMaritialstatus());
        System.out.println(flattenedemployee.getUtctimestamp());

        String unflatternJSONString = readerWriter.unflattenTheJSON(flatternJSONString);
        System.out.println(unflatternJSONString);

       // Employee unflattenedEmployee = readerWriter.convertJSONToObject(unflatternJSONString, Employee.class);
        String outputJSON = readerWriter.convertObjectToJSON(flattenedemployee);
        System.out.println(outputJSON);
    }
    
    private static String getJSONString() {
       return "{\n" +
                "  \"id\": 123,\n" +
                "  \"name\": \"Henry Smith\",\n" +
                "  \"age\": 28,\n" +
                "  \"salary\": 2000,\n" +
                "  \"designation\": \"Programmer\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Park Avn.\",\n" +
                "    \"city\": \"Westchester\",\n" +
                "    \"zipcode\": 10583\n" +
                "  },\n" +
                "  \"phoneNumbers\": [\n" +
                "    654321,\n" +
                "    222333\n" +
                "  ],\n" +

                "  \"personalInformation\": {\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"maritialstatus\": \"Married\",\n" +
                "    \"utctimestamp\": \"2012-04-23T18:25:43.511Z\"\n" +
                "  }\n" +
                "}";
    }
}
