package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/* 
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String key = null;

        HashMap<String, Person> persons = new HashMap<>()
        {{
            put("user", new Person.User());
            put("loser",new Person.Loser());
            put("coder", new Person.Coder());
            put("proger", new Person.Proger());
        }};

        while (persons.containsKey(key = reader.readLine())) {
            doWork(persons.get(key));
        }
    }

    public static void doWork(Person person) {
        if (person instanceof Person.User)
            ((Person.User) person).live();
        else if (person instanceof Person.Loser)
            ((Person.Loser) person).doNothing();
        else if (person instanceof Person.Coder)
            ((Person.Coder) person).writeCode();
        else if (person instanceof Person.Proger)
            ((Person.Proger) person).enjoy();
    }
}
