package com.suhas.springboot.testfiltering;

import com.suhas.springboot.domain.JSONDomain;
import com.suhas.springboot.domain.Student;
import com.suhas.springboot.flattern.StudentGenericJSONReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class FilteringCriteria implements JSONDomain {
    private String key;
    private Object value;
    private String operator;

    public FilteringCriteria(
            String key,
            Object value,
            String operator) {
        this.key = key;
        this.value = value;
        this.operator = operator;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "FilteringCriteria{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}

public class DynamicFiltering {

    List<Map<String, Object>> studentList;
    List<FilteringCriteria> filteringCriteriaList;
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicFiltering.class);

    DynamicFiltering(
            final List<Map<String, Object>> studentList,
            final List<FilteringCriteria> filteringCriteriaList) {
        this.studentList = studentList;
        this.filteringCriteriaList = filteringCriteriaList;
    }

    List<Map<String, Object>>  composePredicates() {
        System.out.println();
        System.out.println("Composed predicates:");
        System.out.println();

        List<Predicate<Map.Entry<String, Object>>> allPredicates = buildPredicates();

        /*List<Map<String, Object>> resultStudentList = new ArrayList<>();
        allPredicates.
                stream().forEach(predicate -> {
            studentList.stream().forEach(studentListObject -> {
                         studentListObject.entrySet().forEach(entry -> {
                                if(predicate.test(entry)) {
                                    resultStudentList.add(studentListObject);
                              };
                         });
                    }
            );
        });*/

        Predicate<Map.Entry<String, Object>> compositePredicate = allPredicates.stream().reduce(w -> true, Predicate::and);

        List<Map<String, Object>> resultStudentList = studentList.stream().filter(p -> p.entrySet().stream().anyMatch(compositePredicate)).collect(Collectors.toList());

        System.out.println(resultStudentList);
        return resultStudentList;
    }

    /**
     * Creates a Criterion from a Predicate.
     */
    List<Predicate<Map.Entry<String, Object>>> buildPredicates() {
        List<Predicate<Map.Entry<String, Object>>> predicates = new ArrayList<>();
        for(FilteringCriteria filteringCriteria : filteringCriteriaList) {
            String key = filteringCriteria.getKey();
            Object value = filteringCriteria.getValue();
            String operation = filteringCriteria.getOperator();

            PredicateGenerator predicateGenerator = new PredicateGenerator();
            Predicate<Map.Entry<String, Object>> keyPredicate = entry -> entry.getKey().contains(key);
            Predicate<Map.Entry<String, Object>> valuePredicate = predicateGenerator.preaparePredicate(value, OperationTypes.getOperationTypes(operation));

            predicates.add(keyPredicate.and(valuePredicate));
        }
        return predicates;
    }

    private static String getJSONString() {
        return "{\n" +
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
                "}";
    }

    private static List<Map<String, Object>> usingFlattenMap() {
        String jsonString = getJSONString();
        LOGGER.info("JSON  ::" + jsonString);

        StudentGenericJSONReaderWriter<Student> readerWriter = new StudentGenericJSONReaderWriter<>();
        Map<String, Object> flattenedJsonAsMap = readerWriter.flattenTheJSONAsMap(jsonString);
        LOGGER.info("Flt JSON  ::" + flattenedJsonAsMap);

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


    public static void main(String[] args) throws Exception{
        List<Map<String, Object>> studentDetails = usingFlattenMap();
        LOGGER.info("JSON As List of Map   ::" + studentDetails);

        List<FilteringCriteria> filteringCriteria = new ArrayList<>();
        //FilteringCriteria filteringCriteria1 = new FilteringCriteria("rollnumber", 123,"equals");
        FilteringCriteria filteringCriteria2 = new FilteringCriteria("subject.subjectname", "Hindi", "equals");
        //FilteringCriteria filteringCriteria3 = new FilteringCriteria("dob", "1987-09-16T01:01:56.300Z","equals");

        //FilteringCriteria filteringCriteria4 = new FilteringCriteria("age", 32,"greater than");
        FilteringCriteria filteringCriteria4 = new FilteringCriteria("age", 32,"greater than or equals to");
        //FilteringCriteria filteringCriteria4 = new FilteringCriteria("age", 33,"less than");
        //FilteringCriteria filteringCriteria4 = new FilteringCriteria("age", 32,"less than or equals to");

        FilteringCriteria filteringCriteria5 = new FilteringCriteria("isActive", false,"equals");

        //filteringCriteria.add(filteringCriteria1);
        filteringCriteria.add(filteringCriteria2);
        //filteringCriteria.add(filteringCriteria3);
        //filteringCriteria.add(filteringCriteria4);
        filteringCriteria.add(filteringCriteria5);

        DynamicFiltering dynamicFiltering = new DynamicFiltering(studentDetails, filteringCriteria);
        dynamicFiltering.composePredicates();
    }
}
