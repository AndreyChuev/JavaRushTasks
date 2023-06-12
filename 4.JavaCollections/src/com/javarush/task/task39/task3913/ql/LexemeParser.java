package com.javarush.task.task39.task3913.ql;

import com.javarush.task.task39.task3913.Event;
import com.javarush.task.task39.task3913.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LexemeParser {

    private static final String REGEX = "\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    private static final Predicate<String> IP_PREDICATE = s -> s.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$");
    private static final Predicate<String> DATE_PREDICATE = s -> s.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}");
    private static final Predicate<String> USER_PREDICATE = s -> s.matches("[A-Z a-z]+");
    private static final Predicate<String> EVENT_PREDICATE = s -> {
        try {
            Event.valueOf(s);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    };
    private static final Predicate<String> STATUS_PREDICATE = s -> {
        try {
            Status.valueOf(s);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    };

    public static List<Lexeme> parseQuery(String query) {
        String[] queryParts = query.split(REGEX);
        List<Lexeme> lexemes = new ArrayList<>();

        for (int i = 0; i < queryParts.length; i++) {
            String part = queryParts[i];

            if (part.equals("=")) {
                lexemes.add(new Lexeme(LexemeType.EQUAL, part));
            } else if (part.startsWith("\"")) {
                part = removeQuotes(part);
                if (IP_PREDICATE.test(part)) {
                    lexemes.add(new Lexeme(LexemeType.IP_VAL, part));
                } else if (DATE_PREDICATE.test(part)) {
                    lexemes.add(new Lexeme(LexemeType.DATE_VAL, part));
                } else if (EVENT_PREDICATE.test(part)) {
                    lexemes.add(new Lexeme(LexemeType.EVENT_VAL, part));
                } else if (STATUS_PREDICATE.test(part)) {
                    lexemes.add(new Lexeme(LexemeType.STATUS_VAL, part));
                } else if (USER_PREDICATE.test(part)) {
                    lexemes.add(new Lexeme(LexemeType.USER_VAL, part));
                } else {
                    throw new IllegalArgumentException("Query error! " + query);
                }
            } else if (part.equals("get")) {
                lexemes.add(new Lexeme(LexemeType.GET_COMMAND, part));
            } else if (part.equals("for")) {
                lexemes.add(new Lexeme(LexemeType.FOR_COMMAND, part));
            } else if (part.equals("and")) {
                lexemes.add(new Lexeme(LexemeType.AND, part));
            } else if (part.equals("between")) {
                lexemes.add(new Lexeme(LexemeType.BETWEEN, part));
            } else if (part.equals("ip")) {
                lexemes.add(new Lexeme(LexemeType.IP, part));
            } else if (part.equals("user")) {
                lexemes.add(new Lexeme(LexemeType.USER, part));
            } else if (part.equals("date")) {
                lexemes.add(new Lexeme(LexemeType.DATE, part));
            } else if (part.equals("event")) {
                lexemes.add(new Lexeme(LexemeType.EVENT, part));
            } else if (part.equals("status")) {
                lexemes.add(new Lexeme(LexemeType.STATUS, part));
            } else {
                throw new IllegalArgumentException("Query error! " + query);
            }
        }

        return lexemes;
    }

    private static String removeQuotes(String str) {
        if (str.charAt(0) == '"' && str.charAt(str.length() - 1) == '"') {
            return str.substring(1, str.length() - 1);
        }
        throw new IllegalArgumentException("Quotes is not found! " + str);
    }
}
