package com.javarush.task.task39.task3913.ql.predicates;

import com.javarush.task.task39.task3913.Log;

import java.util.Date;
import java.util.function.Predicate;

public class BetweenPredicate implements Predicate<Log> {

    private final Date after;
    private final Date before;

    public BetweenPredicate(Date after, Date before) {
        this.after = after;
        this.before = before;
    }

    @Override
    public boolean test(Log log) {
        Date logDate = log.date();
        return (after == null || logDate.after(after)) && (before == null || logDate.before(before));
    }
}
