package com.javarush.task.task34.task3404.calculator.parser;

import java.util.ArrayList;
import java.util.List;

public class LexemeBuffer {
    private int pos;
    private final List<Lexeme> lexemes;

    public LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Lexeme next() {
        return lexemes.get(pos++);
    }

    public int getOperationCount() {
        int count = 0;
        LexemeType[] operators = new LexemeType[] {
                LexemeType.OP_MUL, LexemeType.OP_DIV, LexemeType.OP_PLUS, LexemeType.OP_MINUS, LexemeType.OP_POW,
                LexemeType.NAME
        };
        for (Lexeme lexeme : lexemes) {
            for (LexemeType operator : operators) {
                if (operator == lexeme.getType())
                    count++;
            }
        }
        return count;
    }

    public void back() {
        pos--;
    }

    public int getPos() {
        return pos;
    }
}
