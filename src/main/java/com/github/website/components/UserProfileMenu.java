package com.github.website.components;

import com.github.website.base.BasePage;
import com.github.base.driver.DriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserProfileMenu extends BasePage {

    @FindBy(css = "summary[aria-label='View profile and more']")
    private WebElement userProfileMenu;

    @FindBy(linkText = "Settings")
    private WebElement settingsMenuItemLink;

    @FindBy(linkText = "Your gists")
    private WebElement yourGistsMenuItemLink;

    @Inject
    public UserProfileMenu(DriverManager driverManager) {
        super(driverManager);
    }

    public void waitForUserProfileMenuLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='user-links']/li[3]/details")));
    }

    public void openUserProfileMenu() {
        userProfileMenu.click();
    }

    public void waitForUserProfileMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='user-links']/li[3]/details/ul")));
    }

    public void openProfileSettingsPage() {
        settingsMenuItemLink.click();
    }

    public void openYourGistPage() {
        yourGistsMenuItemLink.click();
    }

}
