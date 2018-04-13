package com.github.logging;

import org.apache.logging.log4j.Logger;

public class BaseLogger {
    private Logger logger = LoggerInstanceProvider.getLogger(BaseLogger.class);

    public void error(String msg) {
        logger.error(msg);
    }

    public void info(String msg, Object p0, Object p1) {
        logger.info(msg, p0, p1);
    }

    public void info(String msg) {
        logger.info(msg);
    }
}
