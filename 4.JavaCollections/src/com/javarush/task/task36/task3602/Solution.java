package com.javarush.task.task36.task3602;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.*;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        return Arrays.stream(Collections.class.getDeclaredClasses())
                .filter(aClass -> aClass.getName().equals("java.util.Collections$EmptyList"))
                .findFirst()
                .get();
    }


}
