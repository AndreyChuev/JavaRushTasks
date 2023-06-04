package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.ql.LexemeBuffer;
import com.javarush.task.task39.task3913.ql.LexemeParser;

import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("D:\\IDEA_project\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
//        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.execute("get event for date = \"03.01.2014 03:45:23\""));

//        String inputString = "get event for date = \"03.01.2014 03:45:23\"";
//        LexemeBuffer buffer = new LexemeBuffer(LexemeParser.parseQuery(inputString));
//        System.out.println(buffer);
    }
}