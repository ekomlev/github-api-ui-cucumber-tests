package com.github.website.base;

import com.github.base.driver.TestContextManager;
import com.google.inject.Inject;

import java.lang.reflect.InvocationTargetException;

public class PageFactory {
    @Inject
    private TestContextManager testContextManager;

    public <P extends BasePage> P initPage(Class<P> classElement) {
        P page = null;
        try {
            page = classElement
                    .getConstructor(TestContextManager.class)
                    .newInstance(testContextManager);
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException();
        }
        return page;
    }
}
