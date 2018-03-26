package com.github.website.pages;

import com.github.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewRepositoryPage extends BasePage {

    @FindBy(id = "repository_name")
    WebElement inputRepositoryNameField;

    @FindBy(id = "repository_description")
    WebElement inputRepositoryDescriptionField;

    @FindBy(id = "repository_public_true")
    WebElement repositoryPublicAccessRadioButton;

    @FindBy (xpath = "//button[@type='submit' and contains(text(), 'Create repository')]")
    WebElement submitCreatingNewRepositoryButton;

    public NewRepositoryPage(WebDriver driver) {
        super(driver);
    }

    public void waitForNewRepositoryForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='new_repository']")));
    }

    public void saveNewRepository(String repositoryName, String repositoryDescription, boolean repositoryPublicAccess) {
        inputRepositoryNameField.sendKeys(repositoryName);
        inputRepositoryDescriptionField.sendKeys(repositoryDescription);
        if (!(repositoryPublicAccess && repositoryPublicAccessRadioButton.isSelected())) {
            repositoryPublicAccessRadioButton.click();
        }
        submitCreatingNewRepositoryButton.click();
    }

}
