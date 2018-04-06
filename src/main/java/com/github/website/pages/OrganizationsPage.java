package com.github.website.pages;

import com.github.base.BasePage;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrganizationsPage extends BasePage {

    @FindBy (xpath = "//div[@class='Box']/div[@class='Box-row d-flex flex-items-center']//a")
    private List<WebElement> organisationItem;

    @FindBy (xpath = "//a[@class='pagehead-tabs-item ' and position()='5']")
    private WebElement settingsMenuItemLink;

    @FindBy (xpath = "//a[contains(text(), 'Delete this organization')]")
    private WebElement deleteThisOrganizationButton;

    @FindBy (xpath = "//div[@class='facebox-content dangerzone']/form[@id='cancel_plan']/p/label/input")
    private WebElement deleteConfirmationOrganizationInputField;

    @FindBy (xpath = "//div[@class='facebox-content dangerzone']/form[@id='cancel_plan']/button[@type='submit']")
    private WebElement confirmationOfDeletingButton;

    @Inject
    public OrganizationsPage(WebDriver driver) {
        super(driver);
    }

    public void waitForOrganizationsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Box")));
    }

    public void waitForOrganizationSettings() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-9 float-left']")));
    }

    public void waitForOrganizationNavigationMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orgnav")));
    }

    public void waitForOrganizationContent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='org-profile container js-pinned-repos-reorder-container']")));
    }

    public WebElement createdOrganizationAlreadyExists(String organizationName) {
        for (WebElement organization : organisationItem) {
            if (organization.getText().contains(organizationName)){
                return organization;
            }
        }
        return null;
    }

    public void enterExistingOrganization(WebElement organizationItem) {
        organizationItem.click();
    }

    public void openOrganizationSettings() {
        settingsMenuItemLink.click();
    }

    public void deleteExistingOrganization(String organizationName) {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollBy(0,250)", "");
        deleteThisOrganizationButton.click();
        deleteConfirmationOrganizationInputField.sendKeys(organizationName);
        confirmationOfDeletingButton.click();
    }
}
