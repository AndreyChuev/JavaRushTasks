package com.javarush.task.task39.task3913.ql;

import java.util.Collections;
import java.util.List;

public class LexemeBuffer {

    private int pos;
    private final List<Lexeme> lexemes;

    public LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = Collections.unmodifiableList(lexemes);
    }

    public boolean hasNext() {
        return pos < lexemes.size();
    }

    public Lexeme next() {
        return lexemes.get(pos++);
    }

    public Lexeme lookingAhead() {
        if (hasNext()) {
            return lexemes.get(pos + 1);
        }
        return null;
    }

    public void back() {
        pos--;
    }

    public int pos() {
        return pos;
    }

    @Override
    public String toString() {
        return "LexemeBuffer{" +
                "pos=" + pos +
                ", lexemes=" + lexemes +
                '}';
    }
}
