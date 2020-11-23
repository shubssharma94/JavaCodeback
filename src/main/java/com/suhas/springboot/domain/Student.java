package com.suhas.springboot.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.suhas.springboot.jsonconverter.deserializer.SubjectDeserializer;
import com.suhas.springboot.jsonconverter.deserializer.TimestampDeserializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "rollnumber",
        "name",
        "age",
        "subjects"
})
public class Student implements JSONDomain, Serializable
{

    @JsonProperty("rollnumber")
    private Integer rollnumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("subjects")
    @JsonDeserialize(using = SubjectDeserializer.class)
    private Map<Integer, Subject> subjects = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4730567593566074620L;

    @JsonProperty("rollnumber")
    public Integer getRollnumber() {
        return rollnumber;
    }

    @JsonProperty("rollnumber")
    public void setRollnumber(Integer rollnumber) {
        this.rollnumber = rollnumber;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty("subjects")
    public Map<Integer, Subject> getSubjects() {
        return subjects;
    }

    @JsonProperty("subjects")
    public void setSubjects(Map<Integer, Subject> subjects) {
        this.subjects = subjects;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}