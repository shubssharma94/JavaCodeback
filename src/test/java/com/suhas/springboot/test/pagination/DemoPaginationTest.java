package com.suhas.springboot.test.pagination;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest(classes = DemoPagination.class)
public class DemoPaginationTest {

    private Integer rowLimit = 6;
    private static DemoPagination pagination;
    private static List<Map<String, Object>> studentDetails = new ArrayList<>();

    @BeforeAll
    public static void setup(){
        System.out.println("@BeforeAll executed");
        pagination = new DemoPagination();
        for(int i=1; i<=25; i++) {
            studentDetails.add(usingFlattenMap(i));
        }
    }

    @Test
    public void testPaginationCountOfRecords(){
        List<Map<String, Object>> firstPageRecords = pagination.getPaginationList(1, studentDetails);
        Assertions.assertEquals(6, firstPageRecords.size());

        List<Map<String, Object>> secondPageRecords = pagination.getPaginationList(2, studentDetails);
        Assertions.assertEquals(6, secondPageRecords.size());

        List<Map<String, Object>> thirdPageRecords = pagination.getPaginationList(3, studentDetails);
        Assertions.assertEquals(6, thirdPageRecords.size());

        List<Map<String, Object>> fourthPageRecords = pagination.getPaginationList(4, studentDetails);
        Assertions.assertEquals(6, fourthPageRecords.size());

        List<Map<String, Object>> fifthPageRecords = pagination.getPaginationList(5, studentDetails);
        Assertions.assertEquals(1, fifthPageRecords.size());
    }

    @Test
    public void testPaginationResult(){
        List<Map<String, Object>> firstPageRecords = pagination.getPaginationList(1, studentDetails);
        Assertions.assertEquals("Vikas Naik", firstPageRecords.get(0).get("name").toString());
        Assertions.assertEquals("Skand Naik", firstPageRecords.get(1).get("name").toString());
        Assertions.assertEquals("Swanand Naik", firstPageRecords.get(2).get("name").toString());
        Assertions.assertEquals("Suhas Naik", firstPageRecords.get(3).get("name").toString());
    }

    @Test
    public void testAskForPageThatNotExists(){
        List<Map<String, Object>> pageNotExistsRecordCount = pagination.getPaginationList(6, studentDetails);
        Assertions.assertNotEquals(6, pageNotExistsRecordCount.size());

        List<Map<String, Object>> lastPageRecordCount = pagination.getPaginationList(5, studentDetails);
        Assertions.assertNotEquals(6, lastPageRecordCount.size());
    }


    private static Map<String, Object> usingFlattenMap(
            Integer counter) {
        Map<String, Object> studentInfoMap = new LinkedHashMap<>();
        studentInfoMap.put("rollnumber", counter);

        if(counter%4==0) {
            studentInfoMap.put("name", "Suhas Naik");
        } else if(counter%4==1) {
            studentInfoMap.put("name", "Vikas Naik");
        } else if(counter%4==2) {
            studentInfoMap.put("name", "Skand Naik");
        }  else if(counter%4==3) {
            studentInfoMap.put("name", "Swanand Naik");
        }

        studentInfoMap.put("age", 32);
        studentInfoMap.put("dob", "1987-09-16T01:01:56.300Z");
        studentInfoMap.put("subjectid", 1);
        studentInfoMap.put("subjectname", "Hindi");
        return  studentInfoMap;
    }
}
