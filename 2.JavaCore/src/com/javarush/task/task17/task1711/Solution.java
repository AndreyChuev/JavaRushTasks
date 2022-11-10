package com.javarush.task.task17.task1711;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();
//    public static volatile Scanner scanner = new Scanner(System.in);

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        try {
            switch (args[0]) {
                case "-c":
                    if (!((args.length-1) % 3 == 0))
                        throw new InvalidPropertiesFormatException("Create - Неверный формат данных!");
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length-1; i+=3) {
                            String name = args[i];
                            Sex sex = Sex.parseSex(args[i+1]);
                            Date date = parseDate(args[i+2]);
                            create(allPeople,name,sex,date);
                        }
                    }
                    break;
                case "-u":
                    if (!((args.length-1) % 4 == 0))
                        throw new InvalidPropertiesFormatException("Update - Неверный формат данных!");
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length-1; i+=4) {
                            int id = Integer.parseInt(args[i]);
                            String name = args[i+1];
                            Sex sex = Sex.parseSex(args[i+2]);
                            Date date = parseDate(args[i+3]);
                            update(allPeople,id,name,sex,date);
                        }
                    }
                    break;
                case "-d":
                    for (int i = 1; i < args.length; i++) {
                        for (int j = 0; j < args[i].length(); j++) {
                            if (!(Character.isDigit(args[i].charAt(j))))
                                throw new InvalidPropertiesFormatException("Delete - Неверный формат данных!");
                        }
                    }
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {
                            deleteById(allPeople,Integer.parseInt(args[i]));
                        }
                    }
                    break;
                case "-i":
                    for (int i = 1; i < args.length; i++) {
                        for (int j = 0; j < args[i].length(); j++) {
                            if (!(Character.isDigit(args[i].charAt(j))))
                                throw new InvalidPropertiesFormatException("Read - Неверный формат данных!");
                        }
                    }
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {
                            readById(allPeople,Integer.parseInt(args[i]));
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        synchronized (scanner) {
//           args = scanner.nextLine().split(" ");
//           main(args);
//        }
    }

    public static void create(List<Person> list, String name, Sex sex, Date birthDate) {
        switch (sex) {
            case MALE:
                Person male = Person.createMale(name,birthDate);
                list.add(male);
                System.out.println(list.indexOf(male));
                break;
            case FEMALE:
                Person female = Person.createFemale(name,birthDate);
                list.add(female);
                System.out.println(list.indexOf(female));
        }
    }

    public static void update(List<Person> list, int id, String name, Sex sex, Date birthDate) {
        Person person = list.get(id);
        person.setName(name);
        person.setSex(sex);
        person.setBirthDate(birthDate);
    }

    public static void readById(List<Person> list, int id) {
        System.out.println(list.get(id).toString());
    }

    public static void deleteById(List<Person> list, int id) {
        Person person = list.get(id);
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
