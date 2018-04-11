package com.github.website.components;

import com.github.base.page.BasePage;
import com.github.base.driver.DriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreationNewEntityMenu extends BasePage {

    @FindBy(css = "summary[aria-label~='Create']")
    WebElement creationNewEntityMenu;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-sw']/a[contains(text(), 'New repository')]")
    WebElement newRepositoryMenuItemLink;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-sw']/a[contains(text(), 'New organization')]")
    WebElement newOrganizationMenuItemLink;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-sw']/a[contains(text(), 'New gist')]")
    WebElement newGistMenuItemLink;

    @Inject
    public CreationNewEntityMenu(DriverManager driverManager) {
        super(driverManager);
    }

    public void waitForCreationNewEntityMenuLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='user-links']/li[2]/details")));
    }

    public void openCreationNewEntityMenu() {
        creationNewEntityMenu.click();
    }

    public void waitForCreationNewEntityMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='user-links']/li[2]/details/ul")));
    }

    public void openNewRepositoryPage() {
        newRepositoryMenuItemLink.click();
    }

    public void openNewOrganizationPage() {
        newOrganizationMenuItemLink.click();
    }

    public void openNewGistPage() {
        newGistMenuItemLink.click();
    }
}
