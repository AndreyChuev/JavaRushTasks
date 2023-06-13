package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.ql.Lexeme;
import com.javarush.task.task39.task3913.ql.LexemeBuffer;
import com.javarush.task.task39.task3913.ql.LexemeParser;
import com.javarush.task.task39.task3913.ql.LexemeType;
import com.javarush.task.task39.task3913.ql.predicates.ChainAndPredicate;
import com.javarush.task.task39.task3913.query.*;

import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {

    private static final Predicate<Log> LOG_ALL_PREDICATE = log -> true;

    private final List<Log> logList;

    public LogParser(Path dir) {
        RawLogDataLoader dataLoader = new RawLogDataLoader(dir);
        logList = dataLoader.getRawLogs().stream()
                .map(Log::of)
                .collect(Collectors.toList());
    }

    @Override
    public Set<Object> execute(String query) {
        List<Lexeme> lexemes = LexemeParser.parseQuery(query);
        LexemeBuffer buffer = new LexemeBuffer(lexemes);

        Function<Log, ?> resultFunc = null;

        Lexeme getLexeme = buffer.next();
        if (getLexeme.lexemeType() == LexemeType.GET_COMMAND) {
            Lexeme resultType = buffer.next();
            resultFunc = getResultFunc(resultType);
        } else {
            throw new IllegalArgumentException("Query error: " + query);
        }

        Stream<Log> logStream = logList.stream();

        if (buffer.hasNext()) {
            ChainAndPredicate resultPredicate = new ChainAndPredicate();
            expr(resultPredicate, buffer);
            logStream = logStream.filter(resultPredicate);
        }

        return logStream.map(resultFunc).collect(Collectors.toSet());
    }

    private void expr(ChainAndPredicate chainAndPredicate, LexemeBuffer buffer) {
        while (buffer.hasNext()) {
            Lexeme command = buffer.next();
            switch (command.lexemeType()) {
                case IP:
                    if (buffer.next().lexemeType() == LexemeType.EQUAL) {
                        String ip = buffer.next().value();
                        chainAndPredicate.addPredicate(log -> log.ip().equals(ip));
                    }
                    break;
                case USER:
                    if (buffer.next().lexemeType() == LexemeType.EQUAL) {
                        String user = buffer.next().value();
                        chainAndPredicate.addPredicate(log -> log.user().equals(user));
                    }
                    break;
                case DATE:
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    if (buffer.next().lexemeType() == LexemeType.EQUAL) {
                        try {
                            Date date = dateFormat.parse(buffer.next().value());
                            if (buffer.hasNext() && buffer.lookingAhead().lexemeType() == LexemeType.BETWEEN) {
                                buffer.next();
                                Date after = dateFormat.parse(buffer.next().value());
                                buffer.next();
                                Date before = dateFormat.parse(buffer.next().value());
                                chainAndPredicate.addPredicate(log -> log.date().after(after) && log.date().before(before));
                            } else {
                                chainAndPredicate.addPredicate(log -> log.date().equals(date));
                            }
                        } catch (ParseException e) {
                            throw new IllegalArgumentException(e);
                        }
                    }
                    break;
                case EVENT:
                    if (buffer.next().lexemeType() == LexemeType.EQUAL) {
                        Event event = Event.valueOf(buffer.next().value());
                        chainAndPredicate.addPredicate(log -> log.event() == event);
                    }
                    break;
                case STATUS:
                    if (buffer.next().lexemeType() == LexemeType.EQUAL) {
                        Status status = Status.valueOf(buffer.next().value());
                        chainAndPredicate.addPredicate(log -> log.status() == status);
                    }
                    break;
            }
        }
    }

    private Function<Log, ?> getResultFunc(Lexeme resultType) {
        switch (resultType.lexemeType()) {
            case IP: return Log::ip;
            case USER: return Log::user;
            case DATE: return Log::date;
            case EVENT: return Log::event;
            case STATUS: return Log::status;
            default: throw new IllegalArgumentException("Unknown lexeme " + resultType);
        }
    }

    private Stream<Log> filterByDates(Date after, Date before) {
        Stream<Log> logStream = logList.stream();

        if (after != null) {
            logStream = logStream.filter(log -> log.date().after(after));
        }

        if (before != null) {
            logStream = logStream.filter(log -> log.date().before(before));
        }

        return logStream;
    }

    private Set<String> filterByDatesAndMapToUserSet(Date after, Date before, Predicate<Log> logPredicate) {
        return filterByDates(after, before).filter(logPredicate).map(Log::user).collect(Collectors.toSet());
    }

    private Set<String> getIpWithPredicate(Predicate<Log> logPredicate, Date after, Date before) {
        return filterByDates(after, before)
                .filter(logPredicate)
                .map(log -> log.ip())
                .collect(Collectors.toSet());
    }

    private Set<Date> getDatesWithPredicate(Predicate<Log> logPredicate, Date after, Date before) {
        return filterByDates(after, before)
                .filter(logPredicate)
                .map(Log::date)
                .collect(Collectors.toSet());
    }

    private Set<Event> getEventsWithPredicate(Predicate<Log> logPredicate, Date after, Date before) {
        return filterByDates(after, before)
                .filter(logPredicate)
                .map(Log::event)
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
        return getIpWithPredicate(log -> log.user().equals(user), after, before);
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getIpWithPredicate(log -> log.event() == event, after, before);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getIpWithPredicate(log -> log.status() == status, after, before);
    }

    @Override
    public Set<String> getAllUsers() {
        return logList.stream().map(Log::user).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> true).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return filterByDates(after, before).filter(log -> log.user().equals(user))
                .map(Log::event).collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.ip().equals(ip));
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event() == Event.LOGIN);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event() == Event.DOWNLOAD_PLUGIN);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event() == Event.WRITE_MESSAGE);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event() == Event.SOLVE_TASK);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event() == Event.SOLVE_TASK && log.taskNumber() == task);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event() == Event.DONE_TASK);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return filterByDatesAndMapToUserSet(after, before, log -> log.event() == Event.DONE_TASK && log.taskNumber() == task);
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getDatesWithPredicate(log -> log.user().equals(user) && log.event() == event, after, before);
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getDatesWithPredicate(log -> log.status() == Status.FAILED, after, before);
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getDatesWithPredicate(log -> log.status() == Status.ERROR, after, before);
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return filterByDates(after, before)
                .filter(log -> log.user().equals(user) && log.event() == Event.LOGIN)
                .map(Log::date)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return filterByDates(after, before)
                .filter(log -> log.user().equals(user) && log.taskNumber() == task && log.event() == Event.SOLVE_TASK)
                .map(Log::date)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        return filterByDates(after, before)
                .filter(log -> log.user().equals(user) && log.taskNumber() == task && log.event() == Event.DONE_TASK)
                .map(Log::date)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getDatesWithPredicate(log -> log.user().equals(user) && log.event() == Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getDatesWithPredicate(log -> log.user().equals(user) && log.event() == Event.DOWNLOAD_PLUGIN, after, before);
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getEventsWithPredicate(log -> true, after, before);
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getEventsWithPredicate(log -> log.ip().equals(ip), after, before);
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getEventsWithPredicate(log -> log.user().equals(user), after, before);
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getEventsWithPredicate(log -> log.status() == Status.FAILED, after, before);
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getEventsWithPredicate(log -> log.status() == Status.ERROR, after, before);
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) filterByDates(after, before).filter(log -> log.taskNumber() == task && log.event() == Event.SOLVE_TASK).count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) filterByDates(after, before).filter(log -> log.taskNumber() == task && log.event() == Event.DONE_TASK).count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return filterByDates(after, before)
                .filter(log -> log.event() == Event.SOLVE_TASK)
                .collect(Collectors.toMap(Log::taskNumber, log -> 1, Integer::sum));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return filterByDates(after, before)
                .filter(log -> log.event() == Event.DONE_TASK)
                .collect(Collectors.toMap(Log::taskNumber, log -> 1, Integer::sum));
    }


}