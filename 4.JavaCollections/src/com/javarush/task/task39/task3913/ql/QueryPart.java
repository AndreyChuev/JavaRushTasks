package com.javarush.task.task39.task3913.ql;

import java.util.Arrays;

public enum QueryPart {
    GET_COMMAND("get"),
    IP_QUERY("ip"),
    USER_QUERY("user"),
    DATE_QUERY("date"),
    EVENT_QUERY("event"),
    STATUS_QUERY("status"),
    UNSPECIFIED("unspecified");

    private final String queryValue;

    public static QueryPart of(String strPart) {
        return Arrays.stream(values())
                .filter(queryPart -> queryPart.queryValue.equalsIgnoreCase(strPart))
                .findFirst()
                .orElse(UNSPECIFIED);
    }

    QueryPart(String queryValue) {
        this.queryValue = queryValue;
    }
}
