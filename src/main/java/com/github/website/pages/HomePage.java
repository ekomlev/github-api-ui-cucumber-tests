package com.github.website.pages;

import com.github.website.base.BasePage;
import com.github.base.driver.TestContextManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@id='repos-container']//a[@data-hovercard-type='repository']")
    private List<WebElement> repositoryItem;

    @FindBy(xpath = "//div[contains(@class, 'dashboard-sidebar')]//summary[@title='Switch account context' and @role='button']")
    private WebElement expandAccountSwitcherButton;

    @FindBy(xpath = "//details[contains(@id, 'details-') and @open]//div[@class='SelectMenu-modal']")
    private WebElement accountSwitcherMenu;

    @FindBy(xpath = "//details[contains(@id, 'details-') and @open]//a[@href='/account/organizations' and @role='menuitem']")
    private WebElement manageOrganizationsMenuItemLink;

    @Inject
    public HomePage(TestContextManager driverManager) {
        super(driverManager);
    }

    public WebElement createdRepositoryAlreadyExists(String repositoryName) {
        for (WebElement repository : repositoryItem) {
            if (repository.getText().contains(repositoryName)) {
                return repository;
            }
        }
        return null;
    }

    public void waitForAccountSwitcherMenu() {
        wait.until(ExpectedConditions.visibilityOf(accountSwitcherMenu));
    }

    public void enterExistingRepository(WebElement repositoryItem) {
        repositoryItem.click();
    }

    public void expandAccountSwitcher() {
        expandAccountSwitcherButton.click();
    }

    public void openManageOrganizationsMenuItem() {
        manageOrganizationsMenuItemLink.click();
    }
}
