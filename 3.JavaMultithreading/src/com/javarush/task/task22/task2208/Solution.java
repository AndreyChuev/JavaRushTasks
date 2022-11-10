package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/* 
Формируем WHERE
builder.append(String.format("%s = '%s' end ", s, params.get(s)));
*/

public class Solution {
    public static void main(String[] args) {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("name", null);
//        params.put("country", null);
//        params.put("city", null);
//        params.put("age", "35");
//
//        System.out.println(getQuery(params));
    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        for (String s : params.keySet()) {
            if (s != null && params.get(s) != null) {
                builder.append(s).append(" = ").append("'").append(params.get(s)).append("'").append(" and ");
            }
        }
        try {
            builder.delete(builder.length() - 5, builder.length());
        } catch (IndexOutOfBoundsException e) {
            return builder.toString();
        }
        return builder.toString();
    }
}
