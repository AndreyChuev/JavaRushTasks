package com.javarush.task.task39.task3913.ql;

import java.util.ArrayList;
import java.util.List;

public class LexemeParser {

    private static final String REGEX = "\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public static List<Lexeme> parseQuery(String query) {
        String[] queryParts = query.split(REGEX);
        List<Lexeme> lexemes = new ArrayList<>();

        boolean assignmentSign = false;
        for (String part : queryParts) {
            if (part.equals("=")) {
                lexemes.add(new Lexeme(LexemeType.EQUAL, part));
                assignmentSign = true;
            } else if (assignmentSign) {
                Lexeme valueType = lexemes.get(lexemes.size() - 2);
                part = removeQuotes(part);
                if (valueType.lexemeType() == LexemeType.IP) {
                    lexemes.add(new Lexeme(LexemeType.IP_VAL, part));
                } else if (valueType.lexemeType() == LexemeType.USER) {
                    lexemes.add(new Lexeme(LexemeType.USER_VAL, part));
                } else if (valueType.lexemeType() == LexemeType.DATE) {
                    lexemes.add(new Lexeme(LexemeType.DATE_VAL, part));
                } else if (valueType.lexemeType() == LexemeType.EVENT) {
                    lexemes.add(new Lexeme(LexemeType.EVENT_VAL, part));
                } else if (valueType.lexemeType() == LexemeType.STATUS) {
                    lexemes.add(new Lexeme(LexemeType.STATUS_VAL, part));
                } else {
                    throw new IllegalArgumentException("Query error! " + query);
                }
                assignmentSign = false;
            } else if (part.equals("get")) {
                lexemes.add(new Lexeme(LexemeType.GET_COMMAND, part));
            } else if (part.equals("for")) {
                lexemes.add(new Lexeme(LexemeType.FOR_COMMAND, part));
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
