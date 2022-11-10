package com.javarush.task.task34.task3404;

import com.javarush.task.task34.task3404.calculator.ExpressionCalculator;
import com.javarush.task.task34.task3404.calculator.parser.ExpressionParser;
import com.javarush.task.task34.task3404.calculator.parser.LexemeBuffer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Рекурсия для мат. выражения
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0);


    }

    public void recurse(final String expression, int countOperation) {
        if (expression.matches("^(-\\d+\\.\\d+|-\\d+|\\d+\\.\\d+|\\d+)$")) {
            System.out.println(expression + " " + countOperation);
            return;
        }
        LexemeBuffer buffer = new LexemeBuffer(ExpressionParser.parse(expression));
        ExpressionCalculator calculator = new ExpressionCalculator(buffer);
        recurse(round(calculator.calc()), buffer.getOperationCount());
    }

    public String round(double d) {
        BigDecimal decimal = new BigDecimal(d).setScale(2, RoundingMode.HALF_UP);
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(decimal).replace(',', '.');
    }

    public Solution() {
        //don't delete
    }
}
