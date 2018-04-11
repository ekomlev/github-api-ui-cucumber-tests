package com.github.website.pages;

import com.github.base.page.BasePage;
import com.github.base.driver.DriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewRepositoryPage extends BasePage {

    @FindBy(id = "repository_name")
    private WebElement inputRepositoryNameField;

    @FindBy(id = "repository_description")
    private WebElement inputRepositoryDescriptionField;

    @FindBy(id = "repository_public_true")
    private WebElement repositoryPublicAccessRadioButton;

    @FindBy (xpath = "//button[@type='submit' and contains(text(), 'Create repository')]")
    private WebElement submitCreatingNewRepositoryButton;

    @Inject
    public NewRepositoryPage(DriverManager driverManager) {
        super(driverManager);
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
