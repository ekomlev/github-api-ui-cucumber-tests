package com.github.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerInstanceProvider {

    private LoggerInstanceProvider() {
    }

    public static Logger getLogger(final Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}
