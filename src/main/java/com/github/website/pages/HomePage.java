package com.github.website.pages;

import com.github.base.BasePage;
import com.github.base.browser.DriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy (css = "ul.mini-repo-list li > a > span > span.repo")
    private List<WebElement> repositoryItem;

    @FindBy(xpath = "//summary[@class='btn select-menu-button with-gravatar' and @aria-label='Switch account context']")
    private WebElement expandAccountSwitcherButton;

    @FindBy(xpath = "//div[@class='select-menu-list']/a[@href='/account/organizations' and contains(., 'Manage organizations')]")
    private WebElement manageOrganizationsMenuItemLink;

    @Inject
    public HomePage(DriverManager driverManager) {
        super(driverManager);
    }

    public WebElement createdRepositoryAlreadyExists(String repositoryName) {
        for (WebElement repository : repositoryItem) {
            if (repository.getText().contains(repositoryName)){
                return repository;
            }
        }
        return null;
    }

    public void waitForExpandedAccountSwitcher() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-labelledby='context-switch-title']")));
    }

    public void enterExistingRepository(WebElement repositoryItem) {
        repositoryItem.click();
    }

    public void expandAccountSwitcher() {
        expandAccountSwitcherButton.click();
    }

    public void openOrganizations() {
        manageOrganizationsMenuItemLink.click();
    }

}
