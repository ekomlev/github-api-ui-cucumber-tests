package com.github.logging;

public class PageLogger extends BaseLogger{
    private static PageLogger instance = null;

    private PageLogger() {
    }

    public static PageLogger getInstance() {
        if (instance == null) {
            instance = new PageLogger();
        }
        return instance;
    }

}
