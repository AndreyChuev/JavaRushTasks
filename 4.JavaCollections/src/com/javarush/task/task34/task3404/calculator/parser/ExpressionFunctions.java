package com.javarush.task.task34.task3404.calculator.parser;

import java.util.HashMap;
import java.util.Map;

public class ExpressionFunctions {

    private final Map<String, Function> functionMap;

    public ExpressionFunctions() {
        functionMap = new HashMap<>();
        functionMap.put("cos", args -> {
            if (args.size() != 1)
                throw new IllegalArgumentException("The 'cos' function takes only one argument!");
            return Math.cos(Math.toRadians(args.get(0)));
        });

        functionMap.put("sin", args -> {
            if (args.size() != 1)
                throw new IllegalArgumentException("The 'sin' function takes only one argument!");
            return Math.sin(Math.toRadians(args.get(0)));
        });

        functionMap.put("tan", args -> {
            if (args.size() != 1)
                throw new IllegalArgumentException("The 'tan' function takes only one argument!");
            return Math.tan(Math.toRadians(args.get(0)));
        });
    }

    public Function getFunction(String name) {
        return functionMap.get(name);
    }

    public boolean containsFunc(String name) {
        return functionMap.containsKey(name);
    }
}
