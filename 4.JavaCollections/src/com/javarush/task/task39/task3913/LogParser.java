package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery {

    private final List<Log> logList;

    public LogParser(Path dir) {
        RawLogDataLoader dataLoader = new RawLogDataLoader(dir);
        logList = dataLoader.getRawLogs().stream()
                .map(Log::of)
                .collect(Collectors.toList());
    }

    private Stream<Log> filterByDates(Date after, Date before) {
        Stream<Log> logStream = logList.stream();

        if (after != null) {
            logStream = logStream.filter(log -> log.date.after(after));
        }

        if (before != null) {
            logStream = logStream.filter(log -> log.date.before(before));
        }

        return logStream;
    }

    private Set<String> getIpWithPredicate(Predicate<Log> logPredicate, Date after, Date before) {
        return filterByDates(after, before)
                .filter(logPredicate)
                .map(log -> log.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getIpWithPredicate(log -> true, after, before);
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getIpWithPredicate(log -> log.user.equals(user), after, before);
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getIpWithPredicate(log -> log.event == event, after, before);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getIpWithPredicate(log -> log.status == status, after, before);
    }



    private static class Log {

        String ip;
        String user;
        Date date;
        Event event;
        int taskNumber;
        Status status;

        static Log of(String rawLog) {
            Log log = new Log();
            String[] parts = rawLog.split("\t");

            log.ip = parts[0];
            log.user = parts[1];
            log.date = parseLogDate(parts[2]);

            String[] eventParts = parts[3].split(" ");

            log.event = Event.valueOf(eventParts[0]);
            if (log.event == Event.SOLVE_TASK || log.event == Event.DONE_TASK) {
                log.taskNumber = Integer.parseInt(eventParts[1]);
            }

            log.status = Status.valueOf(parts[4]);

            return log;
        }

        static Date parseLogDate(String date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            try {
                return dateFormat.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }

}