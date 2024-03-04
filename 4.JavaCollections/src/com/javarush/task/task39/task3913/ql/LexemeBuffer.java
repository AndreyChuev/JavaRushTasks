package com.javarush.task.task39.task3913.ql;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        try {
            return lexemes.get(pos++);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Lexeme next(int offset) {
        return lexemes.get(pos + offset);
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
