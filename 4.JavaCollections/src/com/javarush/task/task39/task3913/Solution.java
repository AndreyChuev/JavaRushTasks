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
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.execute("get ip for user = \"Vasya\""));
        System.out.println(logParser.execute("get user for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get event for date = \"11.12.2013 10:11:12\""));
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get event for user = \"Eduard Petrovich Morozko\" and date between \"13.09.2013 5:04:50\" and \"03.01.2014 03:45:23\""));

    }
}