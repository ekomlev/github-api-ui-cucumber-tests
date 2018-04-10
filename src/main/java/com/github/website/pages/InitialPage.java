package com.github.website.pages;

import com.github.base.BasePage;
import com.github.base.browser.DriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitialPage extends BasePage {

    @FindBy(linkText = "Sign in")
    private WebElement signInLink;

    @Inject
    public InitialPage(DriverManager driverManager) {
        super(driverManager);
    }

    public void clickSignInLink() {
        signInLink.click();
        pageInfo("Open SignIn page");
    }

}
