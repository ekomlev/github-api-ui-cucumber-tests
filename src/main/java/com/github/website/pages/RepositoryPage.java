package com.github.website.pages;

import com.github.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RepositoryPage extends BasePage {

    @FindBy (xpath = "//a[@class='js-selected-navigation-item reponav-item' and position()='4']")
    WebElement settingsMenuItemLink;

    @FindBy (xpath = "//button[contains(text(), 'Delete this repository')]")
    WebElement deleteThisRepositoryButton;

    @FindBy (xpath = "//input[@aria-label='Type in the name of the repository to confirm that you want to delete this repository.']")
    WebElement deleteConfirmationRepositoryInputField;

    @FindBy (xpath = "//button[@type='submit' and contains(., 'I understand the consequences, delete this repository')]")
    WebElement confirmationOfDeletingButton;

    public RepositoryPage(WebDriver driver) {
        super(driver);
    }

    public void openRepositorySettings() {
        settingsMenuItemLink.click();
    }

    public void waitForRepositorySettings() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("options_bucket")));
    }

    public void deleteExistingRepository(String repositoryName) {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollBy(0,250)", "");
        deleteThisRepositoryButton.click();
        deleteConfirmationRepositoryInputField.sendKeys(repositoryName);
        confirmationOfDeletingButton.click();
    }

}
