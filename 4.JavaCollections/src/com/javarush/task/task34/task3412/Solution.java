package com.javarush.task.task34.task3412;

//import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        logger.debug("Constructor: value1='{}' value2='{}' value3='{}'", value1, value2, value3);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public static void main(String[] args) {
//        Properties properties;
//        try (InputStream inputStream = new FileInputStream("D:\\IDEA_project\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3412\\log4j.properties")) {
//            properties = new Properties();
//            properties.load(inputStream);
//            PropertyConfigurator.configure(properties);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Solution solution = new Solution(1, "123", new Date());
//        solution.printString();
//        solution.printDateAsLong();
//        solution.divide(3, 2);
    }

    public void calculateAndSetValue3(long value) {
        logger.trace("calculateAndSetValue3: {}", value);
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            logger.debug("calculateAndSetValue3 [value > Integer.MAX_VALUE]: value='{}'", value);
            value1 = (int) (value / Integer.MAX_VALUE);
        } else {
            logger.debug("calculateAndSetValue3 [ ! value > Integer.MAX_VALUE]: value='{}'", value);
            value1 = (int) value;
        }
    }

    public void printString() {
        logger.trace("printString: value2='{}' length='{}'", value2, value2.length());
        if (value2 != null) {
            System.out.println(value2.length());
        }
    }

    public void printDateAsLong() {
        logger.trace("printDateAsLong: value3.getTime='{}'", value3);
        if (value3 != null) {
            System.out.println(value3.getTime());
        }
    }

    public void divide(int number1, int number2) {
        logger.trace("divide: number1 = {}, number2 = {}", number1, number2);
        try {
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            logger.error(e.getMessage());
        }
    }

    public void setValue1(int value1) {
        logger.debug("setValue1: value={}", value1);
        this.value1 = value1;
    }

    public void setValue2(String value2) {
        logger.debug("setValue2: value={}", value2);
        this.value2 = value2;
    }

    public void setValue3(Date value3) {
        logger.debug("setValue3: value={}", value3);
        this.value3 = value3;
    }
}
