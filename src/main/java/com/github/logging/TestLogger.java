package com.github.logging;

public class TestLogger extends BaseLogger {
    private static TestLogger instance = null;

    private TestLogger() {
    }

    public static TestLogger getInstance() {
        if (instance == null) {
            instance = new TestLogger();
        }
        return instance;
    }

    @Override
    public void info(String testName, String msg) {
        logger.info(String.format("==================Test '%1$s' %2$s =====================", testName, msg));
    }

    public void warn(String testName, String msg) {
        logger.warn(String.format("==================Test '%1$s' %2$s =====================", testName, msg));
    }

    public void fatal(String testName, String msg) {
        logger.fatal(String.format("==================Test '%1$s' %2$s ====================", testName, msg));
    }

    public void step(int i, String msg) {
        logger.info(String.format("[Step %1$d]: %2$s", i, msg));
    }

    public void step(String customStepNumber, String msg) {
        logger.info(String.format("[Step %1$s]: %2$s", customStepNumber, msg));
    }

    public void check(String msg) {
        logger.info(String.format("[Check]: %s", msg));
    }
}
