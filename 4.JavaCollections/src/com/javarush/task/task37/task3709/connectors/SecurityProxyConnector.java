package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector {

    private final SecurityChecker checker;
    private final SimpleConnector connector;

    public SecurityProxyConnector(String s) {
        checker = new SecurityCheckerImpl();
        connector = new SimpleConnector(s);
    }

    @Override
    public void connect() {
        if (checker.performSecurityCheck()) {
            connector.connect();
        }
    }
}
