package com.javarush.task.pro.task16.task1604;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* 
День недели рождения твоего
*/

public class Solution {

    static Calendar birthDate = new GregorianCalendar(1995, 5, 24);

    public static void main(String[] args) {
        System.out.println(getDayOfWeek(birthDate));
    }

    static String getDayOfWeek(Calendar calendar) {
        Date date = calendar.getTime();
        SimpleDateFormat formater = new SimpleDateFormat("EEEE", new Locale("RU"));
        String result = formater.format(date);
        return result;
    }
}
