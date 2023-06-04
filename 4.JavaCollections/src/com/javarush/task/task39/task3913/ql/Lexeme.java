package com.javarush.task.task39.task3913.ql;

public class Lexeme {

    private final LexemeType lexemeType;
    private final String value;

    public Lexeme(LexemeType lexemeType, String value) {
        this.lexemeType = lexemeType;
        this.value = value;
    }

    public LexemeType lexemeType() {
        return lexemeType;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "lexemeType=" + lexemeType +
                ", value='" + value + '\'' +
                '}';
    }
}
