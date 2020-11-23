package com.suhas.springboot.sorting;

import com.suhas.springboot.testfiltering.OperationTypes;

public enum  SortDirectionEnum {
    ASC("ascending"),
    DESC("descending");

    private String direction;

    SortDirectionEnum(String direction) {
        this.direction = direction;
    }

    public static SortDirectionEnum getSortDirection(
            String direction) {
        SortDirectionEnum matchedOperationTypes = null;

        for(SortDirectionEnum operationTypes : SortDirectionEnum.values()) {
            if(direction.equals(operationTypes.direction)) {
                matchedOperationTypes = operationTypes;
                break;
            }
        }

        return matchedOperationTypes;
    }
}
