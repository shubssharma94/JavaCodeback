
package com.suhas.springboot.domain;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.suhas.springboot.jsonconverter.deserializer.TimestampDeserializer;
import com.suhas.springboot.jsonconverter.serializer.TimestampSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "age",
        "salary",
        "designation",
        "phoneNumbers",
})
public class Employee implements JSONDomain, Serializable
{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("salary")
    private Integer salary;
    @JsonProperty("designation")
    private String designation;

    @JsonProperty("address.street")
    private String street;

    @JsonProperty("address.city")
    private String city;

    @JsonProperty("address.zipcode")
    private String zipcode;

    @JsonProperty("phoneNumbers")
    private List<Integer> phoneNumbers = null;

    @JsonProperty("personalInformation.gender")
    private String gender;

    @JsonProperty("personalInformation.maritialstatus")
    private String maritialstatus;

    @JsonProperty("personalInformation.utctimestamp")
    @JsonDeserialize(using = TimestampDeserializer.class)
    @JsonSerialize(using = TimestampSerializer.class)
    private Date utctimestamp;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3516968849260107879L;

    @JsonProperty("street")
    public String getStreet() {
        return street;
    }

    @JsonProperty("street")
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("zipcode")
    public String getZipcode() {
        return zipcode;
    }

    @JsonProperty("zipcode")
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
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

    @JsonProperty("salary")
    public Integer getSalary() {
        return salary;
    }

    @JsonProperty("salary")
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @JsonProperty("designation")
    public String getDesignation() {
        return designation;
    }

    @JsonProperty("designation")
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @JsonProperty("phoneNumbers")
    public List<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }

    @JsonProperty("phoneNumbers")
    public void setPhoneNumbers(List<Integer> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("maritialstatus")
    public String getMaritialstatus() {
        return maritialstatus;
    }

    @JsonProperty("maritialstatus")
    public void setMaritialstatus(String maritialstatus) {
        this.maritialstatus = maritialstatus;
    }

    @JsonProperty("utctimestamp")
    public Date getUtctimestamp() {
        return utctimestamp;
    }

    @JsonProperty("utctimestamp")
    public void setUtctimestamp(Date utctimestamp) {
        this.utctimestamp = utctimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(designation, employee.designation) &&
                Objects.equals(street, employee.street) &&
                Objects.equals(city, employee.city) &&
                Objects.equals(zipcode, employee.zipcode) &&
                Objects.equals(phoneNumbers, employee.phoneNumbers) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(maritialstatus, employee.maritialstatus) &&
                Objects.equals(utctimestamp, employee.utctimestamp) &&
                Objects.equals(additionalProperties, employee.additionalProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary, designation, street, city, zipcode, phoneNumbers, gender, maritialstatus, utctimestamp, additionalProperties);
    }
}
