package com.github.website.pages;

import com.github.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {


    @FindBy(css = "#login_field")
    WebElement inputLoginField;

    @FindBy(css = "#password")
    WebElement inputPasswordField;

    @FindBy(css = "input[value='Sign in']")
    WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void waitForAuthorizationForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
    }

    public void fillInAuthorizationForm(String userName, String userPassword) {
        inputLoginField.clear();
        inputLoginField.sendKeys(userName);
        inputPasswordField.clear();
        inputPasswordField.sendKeys(userPassword);
        pagelogger.info("Authorization form was filled in. " + "User name: " + userName + ", password: " + userPassword);
    }

    public void clickSignInButton() {
        signInButton.click();
        pagelogger.info("Open Home page");
    }

}
