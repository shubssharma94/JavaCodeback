package com.suhas.springboot.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suhas.springboot.domain.JSONDomain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class SortingCriteria implements JSONDomain {
    private String key;
    private SortDirectionEnum SortDirection;

    public SortingCriteria(String key, SortDirectionEnum sortDirection) {
        this.key = key;
        SortDirection = sortDirection;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SortDirectionEnum getSortDirection() {
        return SortDirection;
    }

    public void setSortDirection(SortDirectionEnum sortDirection) {
        SortDirection = sortDirection;
    }

    @Override
    public String toString() {
        return "SortingCriteria{" +
                "key='" + key + '\'' +
                ", SortDirection='" + SortDirection + '\'' +
                '}';
    }
}

public class DynamicSortingMultipleCriteria {

    List<Map<String, Object>> studentList;
    SortingCriteria sortingCriteria;
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicSorting.class);

    private static List<Map<String, Object>> compareTo(
            final SortingCriteria sortingCriteria,
            final List<Map<String, Object>> studentList) {
        return studentList.stream().sorted((stringObjectMap, t1) -> {
            Comparable object1 = (Comparable) stringObjectMap.get(sortingCriteria.getKey());
            Comparable object2 = (Comparable) t1.get(sortingCriteria.getKey());

            if(SortDirectionEnum.ASC.equals(sortingCriteria.getSortDirection())) {
                return object1.compareTo(object2);
            } else {
                return object2.compareTo(object1);
            }
        }).collect(Collectors.toList());
    }


    public static void main(String[] args) throws Exception{

        List<Map<String, Object>> studentDetails = getFlattenedListOfMap();
        System.out.println("Original List :: " + studentDetails);

        List<SortingCriteria> sortingCriteriaList = new ArrayList<>();
        SortingCriteria sortingCriteria1 = new SortingCriteria("name", SortDirectionEnum.DESC);
        SortingCriteria sortingCriteria2 = new SortingCriteria("rollnumber", SortDirectionEnum.ASC);
        sortingCriteriaList.add(sortingCriteria1);
        sortingCriteriaList.add(sortingCriteria2);

        final List<Map<String, Object>> resultantList = new ArrayList<>(studentDetails);
        sortingCriteriaList.stream().forEach(sortingCriteria -> {
            List<Map<String, Object>> sortedList = compareTo(sortingCriteria, resultantList);
            resultantList.clear();
            resultantList.addAll(sortedList);
            System.out.println(resultantList);
        });

        System.out.println("Final List :: " + resultantList);

    }

    private static List<Map<String, Object>> getFlattenedListOfMap() {

        List<Map<String, Object>> studentDetails = new ArrayList<>();
        Map<String, Object> studentInfoMap = new LinkedHashMap<>();
        studentInfoMap.put("rollnumber", 119);
        studentInfoMap.put("name", "Suhas Naik");
        studentInfoMap.put("age", 32);
        studentInfoMap.put("dob", "1987-09-16T01:01:56.300Z");
        studentInfoMap.put("subjectid", 1);
        studentInfoMap.put("subjectname", "Hindi");
        studentDetails.add(studentInfoMap);

        Map<String, Object> studentInfoMap7 = new LinkedHashMap<>();
        studentInfoMap7.put("rollnumber", 120);
        studentInfoMap7.put("name", "Kalyani Naik");
        studentInfoMap7.put("age", 36);
        studentInfoMap7.put("dob", "1984-03-03T01:01:56.300Z");
        studentInfoMap7.put("subject.subjectid", 2);
        studentInfoMap7.put("subject.subjectname", "English");
        studentDetails.add(studentInfoMap7);


        Map<String, Object> studentInfoMap2 = new LinkedHashMap<>();
        studentInfoMap2.put("rollnumber", 121);
        studentInfoMap2.put("name", "Skand Naik");
        studentInfoMap2.put("age", 32);
        studentInfoMap2.put("dob", "1987-09-16T01:01:56.300Z");
        studentInfoMap2.put("subjectid", 2);
        studentInfoMap2.put("subjectname", "English");
        studentDetails.add(studentInfoMap2);

        Map<String, Object> studentInfoMap5 = new LinkedHashMap<>();
        studentInfoMap5.put("rollnumber", 122);
        studentInfoMap5.put("name", "Vikas Naik");
        studentInfoMap5.put("age", 34);
        studentInfoMap5.put("dob", "1987-09-16T01:01:56.300Z");
        studentInfoMap5.put("subjectid", 2);
        studentInfoMap5.put("subjectname", "English");
        studentDetails.add(studentInfoMap5);


        Map<String, Object> studentInfoMap6 = new LinkedHashMap<>();
        studentInfoMap6.put("rollnumber", 123);
        studentInfoMap6.put("name", "Unnati Naik");
        studentInfoMap6.put("age", 36);
        studentInfoMap6.put("dob", "1984-03-03T01:01:56.300Z");
        studentInfoMap6.put("subjectid", 1);
        studentInfoMap6.put("subjectname", "Hindi");
        studentDetails.add(studentInfoMap6);

        Map<String, Object> studentInfoMap3 = new LinkedHashMap<>();
        studentInfoMap3.put("rollnumber", 124);
        studentInfoMap3.put("name", "Swanand Naik");
        studentInfoMap3.put("age", 32);
        studentInfoMap3.put("dob", "1987-09-16T01:01:56.300Z");
        studentInfoMap3.put("subjectid", 2);
        studentInfoMap3.put("subjectname", "Urdu");
        studentDetails.add(studentInfoMap3);

        return studentDetails;
    }
}
