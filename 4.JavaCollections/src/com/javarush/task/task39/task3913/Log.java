package com.javarush.task.task39.task3913;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Log {

    private String ip;
    private String user;
    private Date date;
    private Event event;
    private int taskNumber;
    private Status status;

    public String ip() {
        return ip;
    }

    public String user() {
        return user;
    }

    public Date date() {
        return date;
    }

    public Event event() {
        return event;
    }

    public int taskNumber() {
        return taskNumber;
    }

    public Status status() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return taskNumber == log.taskNumber && Objects.equals(ip, log.ip) && Objects.equals(user, log.user) && Objects.equals(date, log.date) && event == log.event && status == log.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, user, date, event, taskNumber, status);
    }
}
