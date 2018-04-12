package com.github.base.page;

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
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            throw new RuntimeException();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return page;
    }
}
