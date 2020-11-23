package com.suhas.springboot.testfiltering;

public enum OperationTypes {
    EQUALS("equals"),
    NOT_EQUALS("NOT equals"),
    GREATER_THAN("greater than"),
    GREATER_THAN_OR_EQ_TO("greater than or equals to"),
    LESS_THAN("less than"),
    LESS_THAN_OR_EQ_TO("less than or equals to"),
    CONTAINS("contains"),
    ENDS_WITH("ends with"),
    STARTS_WITH("starts with"),
    NOT_CONTAINS("not contains");

    private String operationType;

    private OperationTypes(String operationType) {
        this.operationType = operationType;
    }

    public static OperationTypes getOperationTypes(
            String operationTypeString) {
        OperationTypes matchedOperationTypes = null;

        for(OperationTypes operationTypes : OperationTypes.values()) {
            if(operationTypeString.equals(operationTypes.operationType)) {
                matchedOperationTypes = operationTypes;
                break;
            }
        }

        return matchedOperationTypes;
    }


}
