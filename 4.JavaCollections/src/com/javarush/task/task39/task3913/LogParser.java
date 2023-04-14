package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery {

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

    private Set<String> filterByDatesAndMapToUserSet(Date after, Date before, Predicate<Log> logPredicate) {
        return filterByDates(after, before).filter(logPredicate).map(Log::getUser).collect(Collectors.toSet());
    }

    private Set<String> getIpWithPredicate(Predicate<Log> logPredicate, Date after, Date before) {
        return filterByDates(after, before)
                .filter(logPredicate)
                .map(log -> log.ip)
                .collect(Collectors.toSet());
    }

    private Set<Date> getDatesWithPredicate(Predicate<Log> logPredicate, Date after, Date before) {
        return filterByDates(after, before)
                .filter(logPredicate)
                .map(Log::getDate)
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

    @Override
    public Set<String> getAllUsers() {
        return logList.stream().map(Log::getUser).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> true).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return filterByDates(after, before).filter(log -> log.user.equals(user))
                .map(Log::getEvent).collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.ip.equals(ip));
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event == Event.LOGIN);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event == Event.DOWNLOAD_PLUGIN);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event == Event.WRITE_MESSAGE);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event == Event.SOLVE_TASK);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event == Event.SOLVE_TASK && log.taskNumber == task);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event == Event.DONE_TASK);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event == Event.DONE_TASK && log.taskNumber == task);
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getDatesWithPredicate(log -> log.user.equals(user) && log.event == event, after, before);
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getDatesWithPredicate(log -> log.status == Status.FAILED, after, before);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getDatesWithPredicate(log -> log.status == Status.ERROR, after, before);
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return filterByDates(after, before)
                .filter(log -> log.event == Event.LOGIN)
                .findFirst()
                .map(Log::getDate)
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return filterByDates(after, before)
                .filter(log -> log.user.equals(user) && log.taskNumber == task && log.event == Event.SOLVE_TASK)
                .findFirst()
                .map(Log::getDate)
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return filterByDates(after, before)
                .filter(log -> log.user.equals(user) && log.taskNumber == task && log.event == Event.DONE_TASK)
                .findFirst()
                .map(Log::getDate)
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getDatesWithPredicate(log -> log.user.equals(user) && log.event == Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getDatesWithPredicate(log -> log.user.equals(user) && log.event == Event.DOWNLOAD_PLUGIN, after, before);
    }


    private static class Log {

        String ip;
        String user;
        Date date;
        Event event;
        int taskNumber;
        Status status;

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public int getTaskNumber() {
            return taskNumber;
        }

        public Status getStatus() {
            return status;
        }

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