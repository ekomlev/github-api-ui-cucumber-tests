package com.github.website.pages;

import com.github.base.BasePage;
import com.github.entities.User;
import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoginPage extends BasePage {
    private static final String USER_FILENAME = "src/test/resources/user_data.json";

    @FindBy(css = "#login_field")
    WebElement inputLoginField;

    @FindBy(css = "#password")
    WebElement inputPasswordField;

    @FindBy(css = "input[value='Sign in']")
    WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public User createUserFromJson() {
        Gson gson = new Gson();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(USER_FILENAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        User user = gson.fromJson(reader, User.class);
        pagelogger.info("User from json resource was created: " + user);
        return user;
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
