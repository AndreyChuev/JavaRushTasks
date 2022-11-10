package com.javarush.task.task34.task3404.calculator;

import com.javarush.task.task34.task3404.calculator.parser.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExpressionCalculator {

    private final LexemeBuffer buffer;
    private final ExpressionFunctions functions;

    public ExpressionCalculator(LexemeBuffer buffer) {
        this.buffer = buffer;
        this.functions = new ExpressionFunctions();
    }

//    ==================================================
//      RULES
//    ==================================================
//
//    expr: plusminus* EOF
//
//    plusminus: mulrdiv (('+' '-') multdiv)*
//
//    multdiv: factor (('*' '/') factor)*
//
//    factor: unary | func | NUMBER | '(' expr ')'
//
//    unary: '-' factor
//
//    func: NAME '(' (expr (, expr)*)? ')'
//
//    pow: expr '^' expr
//
//    2 + 2 * 2

    public double calc() {
        return expr(buffer);
    }


    private double expr(LexemeBuffer buffer) {
        Lexeme lexeme = buffer.next();

        if (lexeme.getType() == LexemeType.EOF)
            return 0d;

        buffer.back();
        return plusminus(buffer);
    }

    private double plusminus(LexemeBuffer buffer) {
        double value = multdiv(buffer);
        while (true) {
            Lexeme lexeme = buffer.next();
            switch (lexeme.getType()) {
                case OP_PLUS:
                    value += multdiv(buffer);
                    break;
                case OP_MINUS:
                    value -= multdiv(buffer);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                case OP_POW:
                case COMMA:
                    buffer.back();
                    return value;
            }
        }
    }

    private double multdiv(LexemeBuffer buffer) {
        double value = pow(buffer);
        while (true) {
            Lexeme lexeme = buffer.next();
            switch (lexeme.getType()) {
                case OP_MUL:
                    value *= pow(buffer);
                    break;
                case OP_DIV:
                    value /= pow(buffer);
                    break;
                case EOF:
                case NAME:
                case COMMA:
                case OP_PLUS:
                case OP_MINUS:
                case OP_POW:
                case RIGHT_BRACKET:
                    buffer.back();
                    return value;
                default:
                    throw new RuntimeException();
            }
        }
    }

    private double pow(LexemeBuffer buffer) {
        double value = factor(buffer);
        while (true) {
            Lexeme lexeme = buffer.next();
            switch (lexeme.getType()) {
                case OP_POW:
                    return Math.pow(value, factor(buffer));
                case EOF:
                case OP_MUL:
                case OP_DIV:
                case OP_PLUS:
                case OP_MINUS:
                case COMMA:
                case NAME:
                case RIGHT_BRACKET:
                    buffer.back();
                    return value;
                default:
                    throw new RuntimeException();
            }
        }
    }

    private double factor(LexemeBuffer buffer) {
        Lexeme lexeme = buffer.next();
        switch (lexeme.getType()) {
            case OP_MINUS:
                double res = factor(buffer);
                return -res;
            case NAME:
                buffer.back();
                return func(buffer);
            case LEFT_BRACKET:
                double val = expr(buffer);
                lexeme = buffer.next();
                if (lexeme.getType() != LexemeType.RIGHT_BRACKET)
                    throw new RuntimeException("Syntax error: " + lexeme.getValue() + " pos: " + buffer.getPos());
                return val;
            case NUMBER:
                return Double.parseDouble(lexeme.getValue());
            default:
                throw new RuntimeException("Syntax error: " + lexeme.getValue() + " pos: " + buffer.getPos());
        }
    }

    private double func(LexemeBuffer buffer) {
        String funcName = buffer.next().getValue();

        List<Double> args = new ArrayList<>();

        Lexeme lexeme = buffer.next();
        if (lexeme.getType() == LexemeType.LEFT_BRACKET) {
            do {
                args.add(expr(buffer));
                lexeme = buffer.next();

                if (lexeme.getType() != LexemeType.COMMA && lexeme.getType() != LexemeType.RIGHT_BRACKET)
                    throw new IllegalArgumentException("Syntax error " + lexeme + " pos: " + buffer.getPos());

            } while (lexeme.getType() == LexemeType.COMMA);
        }

        return functions.getFunction(funcName).apply(args);
    }


}
