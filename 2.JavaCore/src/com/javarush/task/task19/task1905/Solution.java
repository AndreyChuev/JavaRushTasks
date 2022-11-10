package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
//        TestContact testContact = new TestContact();
//        TestCustomer testCustomer = new TestCustomer();
//
//        RowItem rowItem = new DataAdapter(testCustomer,testContact);
//
//        System.out.println(rowItem.getCompany());
//        System.out.println(rowItem.getContactFirstName());
//        System.out.println(rowItem.getContactLastName());
//        System.out.println(rowItem.getCountryCode());
//        System.out.println(rowItem.getDialString());

    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String result = "";
            String country = customer.getCountryName();
            for (Map.Entry<String, String> entry : countries.entrySet()) {
                String value = entry.getValue();
                if (value.equals(country)) {
                    result = entry.getKey();
                    break;
                }
            }
            return result;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            return contact.getName().split(",")[1].trim();
        }

        @Override
        public String getContactLastName() {
            return contact.getName().split(",")[0].trim();
        }

        @Override
        public String getDialString() {
            String dial = "callto://+";
            Matcher matcher = Pattern.compile("\\d+").matcher(contact.getPhoneNumber());
            while (matcher.find())
                dial += matcher.group();
            return dial;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        String getDialString();         //For example: callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example: +38(050)123-45-67 or +3(805)0123-4567 or +380(50)123-4567 or ...
    }

//    public static class TestContact implements Contact {
//
//        @Override
//        public String getName() {
//            return "Ivanov, Ivan";
//        }
//
//        @Override
//        public String getPhoneNumber() {
//            return "+38(050)123-45-67";
//        }
//    }
//
//    public static class TestCustomer implements Customer {
//
//        @Override
//        public String getCompanyName() {
//            return "JavaRush Ltd.";
//        }
//
//        @Override
//        public String getCountryName() {
//            return "Ukraine";
//        }
//    }

}