package com.github.website.pages;

import com.github.website.base.BasePage;
import com.github.base.driver.TestContextManager;
import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitialPage extends BasePage {

    @FindBy(linkText = "Sign in")
    private WebElement signInLink;

    @Inject
    public InitialPage(TestContextManager driverManager) {
        super(driverManager);
    }

    public void clickSignInLink() {
        signInLink.click();
        pageInfo("Open SignIn page");
    }
}
