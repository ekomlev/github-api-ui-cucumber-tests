package com.github.logging;

public class SystemLogger extends BaseLogger {
    private static SystemLogger instance = null;

    private SystemLogger() {
    }

    public static SystemLogger getInstance() {
        if (instance == null) {
            instance = new SystemLogger();
        }
        return instance;
    }
}
