package com.suhas.springboot.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DynamicSorting {

    List<Map<String, Object>> studentList;
    SortingCriteria sortingCriteria;
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicSorting.class);

    public DynamicSorting(
            List<Map<String, Object>> studentList,
            SortingCriteria sortingCriteria) {
        this.studentList = studentList;
        this.sortingCriteria = sortingCriteria;
    }

    List<Map<String, Object>> sort() {
        System.out.println();
        System.out.println("Sorting :");
        System.out.println();

        Map<String, Object> studentInfoMap = studentList.get(0);
        Object keyValue = studentInfoMap.get(sortingCriteria.getKey());

        if(null != keyValue) {
            if (keyValue instanceof Integer) {
                return studentList.stream().sorted((student1, student2) -> {
                    Integer student1Value = Integer.parseInt(student1.get(sortingCriteria.getKey()).toString());
                    Integer student2Value = Integer.parseInt(student1.get(sortingCriteria.getKey()).toString());

                    SortDirectionEnum sortDirectionEnum = sortingCriteria.getSortDirection();
                    switch (sortDirectionEnum) {
                        case ASC:
                            return student1Value.compareTo(student2Value);
                        case DESC:
                            return student2Value.compareTo(student1Value);
                    }
                    return 0;
                }).collect(Collectors.toList());
            }

            if (keyValue instanceof Double) {
                return studentList.stream().sorted((student1, student2) -> {
                    Double student1Value = Double.parseDouble(student1.get(sortingCriteria.getKey()).toString());
                    Double student2Value = Double.parseDouble(student1.get(sortingCriteria.getKey()).toString());
                    return student1Value.compareTo(student2Value);
                }).collect(Collectors.toList());
            }

            if (keyValue instanceof Boolean) {
                return studentList.stream().sorted((student1, student2) -> {
                    Boolean student1Value = Boolean.parseBoolean(student1.get(sortingCriteria.getKey()).toString());
                    Boolean student2Value = Boolean.parseBoolean(student1.get(sortingCriteria.getKey()).toString());
                    return student1Value.compareTo(student2Value);
                }).collect(Collectors.toList());
            }

            if (keyValue instanceof String) {
                if (keyValue.toString().contains("T")
                        && keyValue.toString().contains("Z")
                        && keyValue.toString().contains(":")
                        && keyValue.toString().contains("-")) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    return studentList.stream().sorted((student1, student2) -> {
                        try {
                            Date student1Value = simpleDateFormat.parse(student1.get(sortingCriteria.getKey()).toString());
                            Date student2Value = simpleDateFormat.parse(student2.get(sortingCriteria.getKey()).toString());
                            return student1Value.compareTo(student2Value);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }).collect(Collectors.toList());
                } else {
                    return studentList.stream().sorted((student1, student2) -> {
                        String student1Value = student1.get(sortingCriteria.getKey()).toString();
                        String student2Value = student2.get(sortingCriteria.getKey()).toString();
                        return student1Value.compareTo(student2Value);
                    }).collect(Collectors.toList());
                }
            }
            return null;
        } else {
            return null;
        }
    }

    private static String getJSONString() {
        return "{\"students\": [" +
                "{\n" +
                "  \"rollnumber\": 123,\n" +
                "  \"name\": \"Suhas Naik\",\n" +
                "  \"age\": 32,\n" +
                "  \"isActive\": true,\n" +
                "  \"dob\": \"1987-09-16T01:01:56.300Z\",\n" +
                "  \"subjects\": [\n" +
                "    \"{\\\"subject\\\":{\\\"subjectid\\\":1, \\\"subjectname\\\":\\\"Hindi\\\"}}\",\n" +
                "    \"{\\\"subject\\\":{\\\"subjectid\\\":2, \\\"subjectname\\\":\\\"English\\\"}}\",\n" +
                "    \"{\\\"subject\\\":{\\\"subjectid\\\":3, \\\"subjectname\\\":\\\"Urdu\\\"}}\"\n" +
                "  ]\n" +
                "}," +

                "{\n" +
                "  \"rollnumber\": 124,\n" +
                "  \"name\": \"Vikas Naik\",\n" +
                "  \"age\": 34,\n" +
                "  \"isActive\": true,\n" +
                "  \"dob\": \"1986-03-03T01:01:56.300Z\",\n" +
                "  \"subjects\": [\n" +
                "    \"{\\\"subject\\\":{\\\"subjectid\\\":1, \\\"subjectname\\\":\\\"Hindi\\\"}}\",\n" +
                "    \"{\\\"subject\\\":{\\\"subjectid\\\":3, \\\"subjectname\\\":\\\"English\\\"}}\"\n" +
                "  ]\n" +
                "}," +

                "{\n" +
                "  \"rollnumber\": 125,\n" +
                "  \"name\": \"Sagar Naik\",\n" +
                "  \"age\": 36,\n" +
                "  \"isActive\": true,\n" +
                "  \"dob\": \"1984-03-03T01:01:56.300Z\",\n" +
                "  \"subjects\": [\n" +
                "    \"{\\\"subject\\\":{\\\"subjectid\\\":1, \\\"subjectname\\\":\\\"Urdu\\\"}}\",\n" +
                "    \"{\\\"subject\\\":{\\\"subjectid\\\":3, \\\"subjectname\\\":\\\"English\\\"}}\"\n" +
                "  ]\n" +
                "}" +

                "]}";
    }

    private static List<Map<String, Object>> usingFlattenMap() {

        List<Map<String, Object>> studentDetails = new ArrayList<>();
        Map<String, Object> studentInfoMap = new LinkedHashMap<>();
        studentInfoMap.put("rollnumber", 119);
        studentInfoMap.put("name", "Suhas Naik");
        studentInfoMap.put("age", 32);
        studentInfoMap.put("dob", "1987-09-16T01:01:56.300Z");
        studentInfoMap.put("subjectid", 1);
        studentInfoMap.put("subjectname", "Hindi");
        studentDetails.add(studentInfoMap);

        Map<String, Object> studentInfoMap2 = new LinkedHashMap<>();
        studentInfoMap2.put("rollnumber", 120);
        studentInfoMap2.put("name", "Suhas Naik");
        studentInfoMap2.put("age", 32);
        studentInfoMap2.put("dob", "1987-09-16T01:01:56.300Z");
        studentInfoMap2.put("subjectid", 2);
        studentInfoMap2.put("subjectname", "English");
        studentDetails.add(studentInfoMap2);

        Map<String, Object> studentInfoMap3 = new LinkedHashMap<>();
        studentInfoMap3.put("rollnumber", 121);
        studentInfoMap3.put("name", "Swanand Naik");
        studentInfoMap3.put("age", 32);
        studentInfoMap3.put("dob", "1987-09-16T01:01:56.300Z");
        studentInfoMap3.put("subjectid", 2);
        studentInfoMap3.put("subjectname", "Urdu");
        studentDetails.add(studentInfoMap3);

        Map<String, Object> studentInfoMap4 = new LinkedHashMap<>();
        studentInfoMap4.put("rollnumber", 122);
        studentInfoMap4.put("name", "Suhas Naik");
        studentInfoMap4.put("age", 34);
        studentInfoMap4.put("dob", "1986-03-03T01:01:56.300Z");
        studentInfoMap4.put("subjectid", 1);
        studentInfoMap4.put("subjectname", "Hindi");
        studentDetails.add(studentInfoMap4);

        Map<String, Object> studentInfoMap5 = new LinkedHashMap<>();
        studentInfoMap5.put("rollnumber", 123);
        studentInfoMap5.put("name", "Vikas Naik");
        studentInfoMap5.put("age", 34);
        studentInfoMap5.put("dob", "1987-09-16T01:01:56.300Z");
        studentInfoMap5.put("subjectid", 2);
        studentInfoMap5.put("subjectname", "English");
        studentDetails.add(studentInfoMap5);

        Map<String, Object> studentInfoMap6 = new LinkedHashMap<>();
        studentInfoMap6.put("rollnumber", 124);
        studentInfoMap6.put("name", "Unnati Naik");
        studentInfoMap6.put("age", 36);
        studentInfoMap6.put("dob", "1984-03-03T01:01:56.300Z");
        studentInfoMap6.put("subjectid", 1);
        studentInfoMap6.put("subjectname", "Hindi");
        studentDetails.add(studentInfoMap6);

        Map<String, Object> studentInfoMap7 = new LinkedHashMap<>();
        studentInfoMap7.put("rollnumber", 125);
        studentInfoMap7.put("name", "Kalyani Naik");
        studentInfoMap7.put("age", 36);
        studentInfoMap7.put("dob", "1984-03-03T01:01:56.300Z");
        studentInfoMap7.put("subject.subjectid", 2);
        studentInfoMap7.put("subject.subjectname", "English");
        studentDetails.add(studentInfoMap7);

        return studentDetails;
    }

    private static List<Map<String, Object>> compareTo(
            final String key,
            final SortDirectionEnum sortDirectionEnum,
            final List<Map<String, Object>> studentList) {
        return studentList.stream().sorted((stringObjectMap, t1) -> {
            Comparable object1 = (Comparable) stringObjectMap.get(key);
            Comparable object2 = (Comparable) t1.get(key);

            if(SortDirectionEnum.ASC.equals(sortDirectionEnum)) {
                return object1.compareTo(object2);
            } else {
                return object2.compareTo(object1);
            }
        }).collect(Collectors.toList());
    }


    public static void main(String[] args) throws Exception{

        List<Map<String, Object>> studentDetails = usingFlattenMap();

        //SortingCriteria sortingCriteria = new SortingCriteria("rollnumber", "descending");
        //DynamicSorting dynamicSorting = new DynamicSorting(studentDetails, sortingCriteria);
        //List<Map<String, Object>> sortedStudentDetails = dynamicSorting.sort();
        //System.out.println(sortedStudentDetails);

        List<Map<String, Object>> sortedStudentDetails = compareTo("rollnumber", SortDirectionEnum.DESC, studentDetails);
        System.out.println("Sorted :: " + sortedStudentDetails);

        List<Map<String, Object>> sortedStudentDetails2 = compareTo("name", SortDirectionEnum.ASC, sortedStudentDetails);
        System.out.println("Sorted :: " + sortedStudentDetails2);

    }
}
