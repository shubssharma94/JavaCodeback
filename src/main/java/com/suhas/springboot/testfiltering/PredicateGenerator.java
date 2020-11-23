package com.suhas.springboot.testfiltering;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Predicate;

public class PredicateGenerator {

    public Predicate<Map.Entry<String, Object>> preaparePredicate(
            Object incomingValueForFilter,
            OperationTypes operation) {

        Predicate<Map.Entry<String, Object>> valuePredicate = null;

        String typeName = incomingValueForFilter.getClass().getTypeName();
        System.out.println(typeName);

        switch (operation) {
            case EQUALS:
                return eqaulsPredicate(incomingValueForFilter);

            case NOT_EQUALS:
                return eqaulsPredicate(incomingValueForFilter).negate();

            case GREATER_THAN:
                return greaterThanPredicate(incomingValueForFilter);

            case GREATER_THAN_OR_EQ_TO:
                return greaterThanEqualToPredicate(incomingValueForFilter);

            case LESS_THAN:
                return lessThanPredicate(incomingValueForFilter);

            case LESS_THAN_OR_EQ_TO:
                return lessThanOrEqualsToPredicate(incomingValueForFilter);

            case CONTAINS:
                valuePredicate = (entry -> entry.getValue().toString().contains(incomingValueForFilter.toString()));
                break;

            case ENDS_WITH:
                valuePredicate = entry -> entry.getValue().toString().endsWith(incomingValueForFilter.toString());
                break;

            case STARTS_WITH:
               valuePredicate = entry -> entry.getValue().toString().startsWith(incomingValueForFilter.toString());
                break;

            case NOT_CONTAINS:
                valuePredicate = (entry -> entry.getValue().toString().contains(incomingValueForFilter.toString()));
                valuePredicate = valuePredicate.negate();
                break;
        }

        return valuePredicate;
    }

    public Predicate<Map.Entry<String, Object>> eqaulsPredicate(
            Object incomingValueForFilter) {
        if(incomingValueForFilter instanceof Integer) {
            return  (entry -> Integer.parseInt(entry.getValue().toString()) == (Integer.parseInt(incomingValueForFilter.toString())));
        }

        if(incomingValueForFilter instanceof Double) {
            return  (entry -> Double.valueOf(entry.getValue().toString()).compareTo((Double.parseDouble(incomingValueForFilter.toString()))) == 0);
        }

        if(incomingValueForFilter instanceof Boolean) {
            return  (entry -> Boolean.valueOf(entry.getValue().toString()).compareTo((Boolean.parseBoolean(incomingValueForFilter.toString()))) == 0);
        }

        if(incomingValueForFilter instanceof String) {
            if(incomingValueForFilter.toString().contains("T")
                    && incomingValueForFilter.toString().contains("Z")
                    && incomingValueForFilter.toString().contains(":")
                    && incomingValueForFilter.toString().contains("-")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return (entry -> {
                    try {
                        return simpleDateFormat.parse(entry.getValue().toString()).equals(simpleDateFormat.parse(incomingValueForFilter.toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                });
            } else {
                return (entry -> entry.getValue().toString().equals(incomingValueForFilter.toString()));
            }
        }

        return null;
    }

    public Predicate<Map.Entry<String, Object>> greaterThanPredicate(
            Object incomingValueForFilter) {
        if(incomingValueForFilter instanceof Integer) {
            return  (entry -> Integer.parseInt(entry.getValue().toString()) > (Integer.parseInt(incomingValueForFilter.toString())));
        }

        if(incomingValueForFilter instanceof Double) {
            return  (entry -> Double.valueOf(entry.getValue().toString()).compareTo((Double.parseDouble(incomingValueForFilter.toString()))) > 0);
        }

        if(incomingValueForFilter instanceof String) {
            if(incomingValueForFilter.toString().contains("T")
                    && incomingValueForFilter.toString().contains("Z")
                    && incomingValueForFilter.toString().contains(":")
                    && incomingValueForFilter.toString().contains("-")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:sm.SSS'Z'");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return (entry -> {
                    try {
                        return simpleDateFormat.parse(entry.getValue().toString()).compareTo(simpleDateFormat.parse(incomingValueForFilter.toString())) > 0;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                });
            } else {
                return (entry -> entry.getValue().toString().compareTo(incomingValueForFilter.toString()) > 0);
            }
        }

        return null;
    }

    public Predicate<Map.Entry<String, Object>> greaterThanEqualToPredicate(
            Object incomingValueForFilter) {
        if(incomingValueForFilter instanceof Integer) {
            return  (entry -> Integer.parseInt(entry.getValue().toString()) >= (Integer.parseInt(incomingValueForFilter.toString())));
        }

        if(incomingValueForFilter instanceof Double) {
            return  (entry -> Double.valueOf(entry.getValue().toString()).compareTo((Double.parseDouble(incomingValueForFilter.toString()))) >= 0);
        }

        if(incomingValueForFilter instanceof String) {
            if(incomingValueForFilter.toString().contains("T")
                    && incomingValueForFilter.toString().contains("Z")
                    && incomingValueForFilter.toString().contains(":")
                    && incomingValueForFilter.toString().contains("-")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:sm.SSS'Z'");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return (entry -> {
                    try {
                        return simpleDateFormat.parse(entry.getValue().toString()).compareTo(simpleDateFormat.parse(incomingValueForFilter.toString())) >= 0;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                });
            } else {
                return (entry -> entry.getValue().toString().compareTo(incomingValueForFilter.toString()) >= 0);
            }
        }
        return null;
    }

    public Predicate<Map.Entry<String, Object>> lessThanPredicate(
            Object incomingValueForFilter) {
        if(incomingValueForFilter instanceof Integer) {
            return  (entry -> Integer.parseInt(entry.getValue().toString()) < (Integer.parseInt(incomingValueForFilter.toString())));
        }

        if(incomingValueForFilter instanceof Double) {
            return  (entry -> Double.valueOf(entry.getValue().toString()).compareTo((Double.parseDouble(incomingValueForFilter.toString()))) < 0);
        }

        if(incomingValueForFilter instanceof String) {
            if(incomingValueForFilter.toString().contains("T")
                    && incomingValueForFilter.toString().contains("Z")
                    && incomingValueForFilter.toString().contains(":")
                    && incomingValueForFilter.toString().contains("-")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:sm.SSS'Z'");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return (entry -> {
                    try {
                        return simpleDateFormat.parse(entry.getValue().toString()).compareTo(simpleDateFormat.parse(incomingValueForFilter.toString())) < 0;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                });
            } else {
                return (entry -> entry.getValue().toString().compareTo(incomingValueForFilter.toString()) < 0);
            }
        }

        return null;
    }

    public Predicate<Map.Entry<String, Object>> lessThanOrEqualsToPredicate(
            Object incomingValueForFilter) {
        if(incomingValueForFilter instanceof Integer) {
            return  (entry -> Integer.parseInt(entry.getValue().toString()) <= (Integer.parseInt(incomingValueForFilter.toString())));
        }

        if(incomingValueForFilter instanceof Double) {
            return  (entry -> Double.valueOf(entry.getValue().toString()).compareTo((Double.parseDouble(incomingValueForFilter.toString()))) <= 0);
        }

        if(incomingValueForFilter instanceof String) {
            if(incomingValueForFilter.toString().contains("T")
                    && incomingValueForFilter.toString().contains("Z")
                    && incomingValueForFilter.toString().contains(":")
                    && incomingValueForFilter.toString().contains("-")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:sm.SSS'Z'");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return (entry -> {
                    try {
                        return simpleDateFormat.parse(entry.getValue().toString()).compareTo(simpleDateFormat.parse(incomingValueForFilter.toString())) <= 0;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                });
            } else {
                return (entry -> entry.getValue().toString().compareTo(incomingValueForFilter.toString()) <= 0);
            }
        }

        return null;
    }
}
