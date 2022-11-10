package com.javarush.task.task22.task2201;

public class StringForFirstThreadTooShortException extends RuntimeException {

    public StringForFirstThreadTooShortException() {
    }

    public StringForFirstThreadTooShortException(String message) {
        super(message);
    }

    public StringForFirstThreadTooShortException(String message, Throwable cause) {
        super(message, cause);
    }

    public StringForFirstThreadTooShortException(Throwable cause) {
        super(cause);
    }
}
