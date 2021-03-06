package com.github.website.components;

import com.github.website.base.BasePage;
import com.github.base.driver.TestContextManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserProfileMenu extends BasePage {

    @FindBy(css = ".Header-link[aria-label^='View profile'][role='button']")
    private WebElement userProfileMenuBtn;

    @FindBy(xpath = "//details[@open]/summary[contains(@aria-label, 'View profile')]/following-sibling::details-menu")
    private WebElement userProfileMenu;

    @FindBy(linkText = "Settings")
    private WebElement settingsMenuItemLink;

    @FindBy(linkText = "Your gists")
    private WebElement yourGistsMenuItemLink;

    @Inject
    public UserProfileMenu(TestContextManager driverManager) {
        super(driverManager);
    }

    public void waitForUserProfileMenuBtn() {
        wait.until(ExpectedConditions.visibilityOf(userProfileMenuBtn));
    }

    public void openUserProfileMenu() {
        userProfileMenuBtn.click();
    }

    public void waitForUserProfileMenu() {
        wait.until(ExpectedConditions.visibilityOf(userProfileMenu));
    }

    public void openProfileSettingsPage() {
        settingsMenuItemLink.click();
    }

    public void openYourGistPage() {
        yourGistsMenuItemLink.click();
    }
}