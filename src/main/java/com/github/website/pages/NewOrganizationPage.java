package com.github.website.pages;

import com.github.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewOrganizationPage extends BasePage {

    @FindBy(id = "organization_login")
    private WebElement inputOrganizationNameField;

    @FindBy(id = "organization_billing_email")
    private WebElement inputOrganizationBillingEmailField;

    @FindBy(xpath = "//input[@type='radio' and @value='free']")
    private WebElement organizationFreePlanRadioButton;

    @FindBy (xpath = "//button[@type='submit' and contains(text(), 'Create organization')]")
    private WebElement submitCreatingNewOrganizationButton;

    @FindBy (xpath = "//a[contains(text(), 'Finish')]")
    private WebElement finishButton;

    public NewOrganizationPage(WebDriver driver) {
        super(driver);
    }

    public void waitForNewOrganizationForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='new-organization']")));
    }

    public void waitForFinishButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Finish')]")));
    }

    public void saveNewOrganization(String organizationName, String organizationBillingEmail, boolean organizationFreePlan) {
        inputOrganizationNameField.sendKeys(organizationName);
        inputOrganizationBillingEmailField.sendKeys(organizationBillingEmail);
        if (!(organizationFreePlan && organizationFreePlanRadioButton.isSelected())) {
            organizationFreePlanRadioButton.click();
        }
        submitCreatingNewOrganizationButton.click();
    }

    public void clickFinishButton() {
        finishButton.click();
    }

}
