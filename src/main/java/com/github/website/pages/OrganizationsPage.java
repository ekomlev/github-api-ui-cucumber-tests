package com.github.website.pages;

import com.github.website.base.BasePage;
import com.github.base.driver.TestContextManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrganizationsPage extends BasePage {

    @FindBy(xpath = "//div[contains(text(), 'Organizations')]/ancestor::div[contains(@class, 'col-9')]/div[@class='Box']")
    private WebElement organizationsList;

    @FindBy(xpath = "//div[@class='Box']/div[@class='Box-row d-flex flex-items-center']//a")
    private List<WebElement> organisationItem;

    @FindBy(css = "nav[aria-label='Organization']")
    private WebElement organisationMenu;

    @FindBy(xpath = "nav[aria-label='Organization'] a[href='/organizations/Organization-xq/settings/profile']")
    private WebElement settingsMenuItemLink;

    @FindBy(xpath = "//h2[contains(text(), 'Organization profile')]/ancestor::div[contains(@class, 'container-lg')]")
    private WebElement organizationsSettingsPage;

    @FindBy(xpath = "//summary[contains(text(), 'Delete this organization')]")
    private WebElement deleteThisOrganizationBtn;

    @FindBy(xpath = "form#cancel_plan input[type='text']")
    private WebElement deleteConfirmationOrganizationInputField;

    @FindBy(xpath = "form#cancel_plan button[type='submit']")
    private WebElement submitDeletionBtn;

    @Inject
    public OrganizationsPage(TestContextManager driverManager) {
        super(driverManager);
    }

    public void waitForOrganizationsList() {
        wait.until(ExpectedConditions.visibilityOf(organizationsList));
    }

    public void waitForOrganizationSettings() {
        wait.until(ExpectedConditions.visibilityOf(organizationsSettingsPage));
    }

    public void waitForOrganizationNavigationMenu() {
        wait.until(ExpectedConditions.visibilityOf(organisationMenu));
    }

    public void waitForOrganizationContent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='org-profile container js-pinned-repos-reorder-container']")));
    }

    public WebElement createdOrganizationAlreadyExists(String organizationName) {
        for (WebElement organization : organisationItem) {
            if (organization.getText().contains(organizationName)) {
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
        JavascriptExecutor jse = (JavascriptExecutor) driverManager.getDriver();
        jse.executeScript("window.scrollBy(0,250)", "");
        deleteThisOrganizationBtn.click();
        deleteConfirmationOrganizationInputField.sendKeys(organizationName);
        submitDeletionBtn.click();
    }
}
