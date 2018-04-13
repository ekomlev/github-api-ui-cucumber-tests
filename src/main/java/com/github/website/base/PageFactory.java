package com.github.website.base;

import com.github.base.driver.DriverManager;
import com.google.inject.Inject;

import java.lang.reflect.InvocationTargetException;

public class PageFactory {
    @Inject
    private DriverManager driverManager;

    public <P extends BasePage> P initPage(Class<P> classElement) {
        P page = null;
        try {
            page = classElement
                    .getConstructor(DriverManager.class)
                    .newInstance(driverManager);
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException();
        }
        return page;
    }
}
