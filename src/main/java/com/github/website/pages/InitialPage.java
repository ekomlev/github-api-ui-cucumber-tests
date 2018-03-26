package com.github.website.pages;

import com.github.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitialPage extends BasePage {

    @FindBy(linkText = "Sign in")
    WebElement signInLink;

    public InitialPage(WebDriver driver) {
        super(driver);
    }

    public void clickSignInLink() {
        signInLink.click();
        pagelogger.info("Open SignIn page");
    }

}
