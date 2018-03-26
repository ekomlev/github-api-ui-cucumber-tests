package com.github.website.pages;

import com.github.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy (css = "ul.mini-repo-list li > a > span > span.repo")
    List<WebElement> repositoryItem;

    @FindBy(xpath = "//summary[@class='btn select-menu-button with-gravatar' and @aria-label='Switch account context']")
    WebElement expandAccountSwitcherButton;

    @FindBy(xpath = "//div[@class='select-menu-list']/a[@href='/account/organizations' and contains(., 'Manage organizations')]")
    WebElement manageOrganizationsMenuItemLink;

    public HomePage(WebDriver driver) {
        super(driver);
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
