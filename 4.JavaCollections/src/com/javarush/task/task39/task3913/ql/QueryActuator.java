package com.javarush.task.task39.task3913.ql;

public class QueryActuator {

    private final QueryPart[] parts;

    public QueryActuator(QueryPart[] parts) {
        this.parts = parts;
    }

    public boolean isGetQuery() {
        return parts[0] == QueryPart.GET_COMMAND;
    }

    public QueryPart getFirstQueryPart() {
        return parts[1];
    }
}
