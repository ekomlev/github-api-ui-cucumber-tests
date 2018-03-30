package com.github.website.pages;

import com.github.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InitialPage extends BasePage {

    @FindBy(linkText = "Sign in")
    private WebElement signInLink;

    public InitialPage(WebDriver driver) {
        super(driver);
    }

    public void clickSignInLink() {
        signInLink.click();
        pageInfo("Open SignIn page");
    }

}
