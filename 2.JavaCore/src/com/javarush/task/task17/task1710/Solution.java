package com.javarush.task.task17.task1710;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        switch (args[0]) {
            case "-c":
                create(args[1], Sex.parseSex(args[2]), args[3]);
                break;
            case "-r":
                read(Integer.parseInt(args[1]));
                break;
            case "-u":
                update(Integer.parseInt(args[1]),args[2], Sex.parseSex(args[3]),args[4]);
                read(Integer.parseInt(args[1]));
                break;
            case "-d":
                delete(Integer.parseInt(args[1]));
                break;
        }
    }

    private static void create(String name, Sex sex, String birthDate) {
        switch (sex) {
            case MALE:
                Person male = Person.createMale(name,parseDate(birthDate));
                allPeople.add(male);
                System.out.println(allPeople.indexOf(male));
                break;
            case FEMALE:
                Person female = Person.createFemale(name,parseDate(birthDate));
                allPeople.add(female);
                System.out.println(allPeople.indexOf(female));
        }
    }

    private static void read(int id) {
        System.out.println(allPeople.get(id).toString());
    }

    private static void update(int id, String name, Sex sex, String birthDate) {
        switch (sex) {
            case MALE:
                allPeople.set(id,Person.createMale(name,parseDate(birthDate)));
            case FEMALE:
                allPeople.set(id,Person.createFemale(name,parseDate(birthDate)));
        }
    }

    private static void delete(int id) {
        Person person = allPeople.get(id);
        person.setName(null);
        person.setSex(null);
        person.setBirthDate(null);
    }

    private static Date parseDate(String date) {
        Date result = null;
        DateFormat slashPattern = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            result = slashPattern.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
