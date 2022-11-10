package com.javarush.task.task34.task3404.calculator.parser;

public enum LexemeType {
    LEFT_BRACKET, RIGHT_BRACKET,
    OP_MUL, OP_DIV, OP_PLUS, OP_MINUS, OP_POW,
    NUMBER, NAME, COMMA,
    EOF
}
