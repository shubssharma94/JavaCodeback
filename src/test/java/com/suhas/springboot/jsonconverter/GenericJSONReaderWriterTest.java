package com.suhas.springboot.jsonconverter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.suhas.springboot.domain.Employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@SpringBootTest(classes = GenericJSONReaderWriter.class)
public class GenericJSONReaderWriterTest {

    private static GenericJSONReaderWriter<Employee> employeeGenericJSONReaderWriter;

    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
        employeeGenericJSONReaderWriter = new GenericJSONReaderWriter<>();

    }

    private Employee getEmployee(
            Integer id,
            String name,
            Integer age,
            String city,
            Date utcTimestamp) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        employee.setSalary(20000);
        employee.setDesignation("Tech Lead");
        employee.setStreet("College Street");
        employee.setZipcode("413709");
        employee.setCity(city);

        List<Integer> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(927065374);
        phoneNumbers.add(727601007);
        employee.setPhoneNumbers(phoneNumbers);

        employee.setGender("Male");
        employee.setUtctimestamp(utcTimestamp);
        return employee;
    }

    private String getJSONString(
            Integer id,
            String name,
            Integer age,
            String city) {
        String jsonString = "{\n" +
                "  \"id\" : " + id +",\n" +
                "  \"name\" : \"" + name + "\",\n" +
                "  \"age\" : " + age + ",\n" +
                "  \"salary\" : 20000,\n" +
                "  \"designation\" : \"Tech Lead\",\n" +
                "  \"phoneNumbers\" : [ 927065374, 727601007 ],\n" +
                "  \"street\" : \"College Street\",\n" +
                "  \"address.street\" : \"College Street\",\n" +
                "  \"address.city\" : \"" + city + "\",\n" +
                "  \"city\" : \"Shrirampur\",\n" +
                "  \"zipcode\" : \"413709\",\n" +
                "  \"address.zipcode\" : \"413709\",\n" +
                "  \"gender\" : \"Male\",\n" +
                "  \"personalInformation.gender\" : \"Male\",\n" +
                "  \"personalInformation.utctimestamp\" : \"2020-10-28T16:40:1040.010Z\",\n" +
                "  \"utctimestamp\" : 1603903210010\n" +
                "}";
        return jsonString;
    }

    @Test
    public void testConvertObjectToJSON(){

        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR, 10);
        date.set(Calendar.MINUTE, 10);
        date.set(Calendar.SECOND, 10);
        date.set(Calendar.MILLISECOND, 10);

        Employee employee = getEmployee(123, "Suhas", 33, "Shrirampur", date.getTime());
        String jsonString = employeeGenericJSONReaderWriter.convertObjectToJSON(employee);
        Assertions.assertEquals(jsonString, getJSONString(123, "Suhas", 33, "Shrirampur"));
    }

    @Test
    public void testConvertJSONToObject(){

        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR, 10);
        date.set(Calendar.MINUTE, 10);
        date.set(Calendar.SECOND, 10);
        date.set(Calendar.MILLISECOND, 10);

        Employee employee = getEmployee(123, "Suhas", 33, "Shrirampur", date.getTime());
        String jsonString = employeeGenericJSONReaderWriter.convertObjectToJSON(employee);


        Employee outputEmployee = employeeGenericJSONReaderWriter.convertJSONToObject(jsonString, Employee.class);
        Assertions.assertEquals(employee, outputEmployee);
    }





}
