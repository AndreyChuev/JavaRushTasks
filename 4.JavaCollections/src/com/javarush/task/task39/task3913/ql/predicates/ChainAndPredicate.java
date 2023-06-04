package com.javarush.task.task39.task3913.ql.predicates;

import com.javarush.task.task39.task3913.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class ChainAndPredicate implements Predicate<Log> {

    private final List<Predicate<Log>> chain = new LinkedList<>();

    public void addPredicate(Predicate<Log> predicate) {
        chain.add(predicate);
    }

    @Override
    public boolean test(Log t) {
        for (Predicate<Log> predicate : chain) {
            if (!predicate.test(t)) {
                return false;
            }
        }
        return true;
    }
}
