package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        String[] parameters = urlParseParameters(url);

        //Pattern parseDouble = Pattern.compile("\\d+\\.\\d+");

        ArrayList<URLParameters> urlParameters = new ArrayList<>();
        for (String parameter : parameters) {
            urlParameters.add(new URLParameters(parameter));
        }

        for (URLParameters urlParameter : urlParameters) {
            System.out.print(urlParameter.getParameter() + " ");
        }

        System.out.println();

//        for (URLParameters urlParameter : urlParameters) {
//            String parameter = urlParameter.getParameter();
//            String value;
//            Matcher matcher;
//            if (parameter.equals("obj")) {
//                value = urlParameter.getValue();
//                matcher = parseDouble.matcher(value);
//                if (matcher.find()) {
//                    alert(Double.parseDouble(value));
//                } else {
//                    alert(value);
//                }
//            }
//        }

        for (URLParameters urlParameter : urlParameters) {
            String parameter = urlParameter.getParameter();
            String value = urlParameter.getValue();
            if (parameter.equals("obj")) {
                try {
                    alert(Double.parseDouble(value));
                } catch (NumberFormatException e) {
                    alert(value);
                }
            }
        }
    }

    private static String[] urlParseParameters(String url) {
        String urlRegex = "?";
        String parRegex = "&";
        String[] parameters;
        String valueLine = url.substring(url.indexOf(urlRegex)+1);
        if (valueLine.contains(parRegex)) {
            parameters = valueLine.split(parRegex);
        } else {
            parameters = new String[1];
            parameters[0] = valueLine;
        }
        return parameters;
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }


    private static class URLParameters {
        private final String parameter, value;

        URLParameters(String parameters) {
            String regex = "=";
            if (parameters.contains(regex)) {
                parameter = parameters.split(regex)[0];
                value = parameters.split(regex)[1];
            } else {
                parameter = parameters;
                value = null;
            }
        }

        public String getParameter() {
            return parameter;
        }

        public String getValue() {
            return value;
        }
    }
}
