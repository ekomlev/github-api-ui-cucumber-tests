package com.github.testcases.reporter;

import com.github.logging.LoggerInstanceProvider;
import org.apache.logging.log4j.Logger;

public class TestReporter {
    private Logger logger = LoggerInstanceProvider.getLogger(TestReporter.class);

    protected void step(int i, String msg) {
        logger.info(String.format("[Step %1$d]: %2$s", i, msg));
    }

    protected void subStep(String customStepNumber, String msg) {
        logger.info(String.format("[Step %1$s]: %2$s", customStepNumber, msg));
    }

    protected void test(String testName, String msg) {
        logger.info(String.format("==================Test '%1$s' %2$s =====================", testName, msg));
    }

    protected void error(String msg) {
        logger.error(msg);
    }

    protected void check(String msg) {
        logger.info(msg);
    }

}
