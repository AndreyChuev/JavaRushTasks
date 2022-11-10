package com.javarush.task.task34.task3404.calculator.parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {
    private static final ExpressionFunctions functions = new ExpressionFunctions();

    public LexemeBuffer parseToBuffer(String exp) {
        return new LexemeBuffer(parse(exp));
    }
    public static List<Lexeme> parse(String exp) {
        List<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < exp.length()) {
            char c = exp.charAt(pos);
            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.OP_MUL, c));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                    pos++;
                    continue;
                case '^':
                    lexemes.add(new Lexeme(LexemeType.OP_POW, c));
                    pos++;
                    continue;
                case ',':
                    lexemes.add(new Lexeme(LexemeType.COMMA, c));
                    pos++;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        StringBuilder sb = new StringBuilder();
                        boolean pointFlag = false;
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= exp.length())
                                break;
                            c = exp.charAt(pos);
                            if (c == '.' && !pointFlag) {
                                sb.append(c);
                                pointFlag = true;
                                pos++;
                                if (pos >= exp.length())
                                    break;
                                c = exp.charAt(pos);
                            }
                        } while (c >= '0' && c <= '9');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (c != ' ') {
                            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                                StringBuilder sb = new StringBuilder();
                                do {
                                    sb.append(c);
                                    pos++;
                                    if (pos >= exp.length())
                                        break;
                                    c = exp.charAt(pos);
                                } while (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z');

                                if (functions.containsFunc(sb.toString())) {
                                    lexemes.add(new Lexeme(LexemeType.NAME, sb.toString()));
                                } else {
                                    throw new RuntimeException("Unknown function name: '" + sb + "'");
                                }
                            }
                        } else {
                            pos++;
                        }
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }
}
