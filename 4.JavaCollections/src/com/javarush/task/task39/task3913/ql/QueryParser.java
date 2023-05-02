package com.javarush.task.task39.task3913.ql;

public class QueryParser {

    public static QueryPart[] parse(String query) {
        String[] stringParts = query.split(" ");
        QueryPart[] result = new QueryPart[stringParts.length];

        for (int i = 0; i < stringParts.length; i++) {
            QueryPart part = QueryPart.of(stringParts[i]);

            if (part == QueryPart.UNSPECIFIED) {
                throw new IllegalArgumentException("QL query error!");
            }

            result[i] = part;
        }

        return result;
    }
}
