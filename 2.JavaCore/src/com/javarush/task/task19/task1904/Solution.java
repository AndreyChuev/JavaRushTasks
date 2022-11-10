package com.javarush.task.task19.task1904;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("D:\\IDEA_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1904\\testfile.txt"));
        PersonScanner personScanner = new PersonScannerAdapter(scanner);
        Person person = personScanner.read();
        Person person1 = personScanner.read();
        System.out.println(person);
        System.out.println(person1);
    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String[] splitLine = fileScanner.nextLine().split(" ");
            Date date = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
                date = dateFormat.parse(splitLine[3] + splitLine[4] + splitLine[5]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Person(splitLine[1], splitLine[2], splitLine[0], date); //0-2 1-1 2-2
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
