package com.github.website;

import com.github.base.BasePage;
import com.github.base.browser.DriverManager;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageFactory {


    public <P extends BasePage> P initPage (Class<P> classElement) {

        try {
            P  page = classElement.getDeclaredConstructor(String.class);
        page.init();
        return page;
        } catch (NoSuchMethodException e) {
        e.printStackTrace();
    }
    }
}
