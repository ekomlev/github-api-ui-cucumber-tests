package com.github.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract public class BaseLogger {
    protected Logger logger = LogManager.getLogger(BaseLogger.class);

    public void info(String o, String msg) {
        logger.info(msg);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void warn(Object o, String msg) {
        logger.warn(msg);
    }

    public void fatal(Object o, String msg) {
        logger.fatal(msg);
    }

}
