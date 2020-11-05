package com.github.website.components;

import com.github.website.base.BasePage;
import com.github.base.driver.TestContextManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreationNewEntityMenu extends BasePage {

    @FindBy(css = ".Header-link[aria-label^='Create new'][role='button']")
    private WebElement creationNewEntityMenuBtn;

    @FindBy(xpath = "//details[@open]/summary[contains(@aria-label, 'Create new')]/following-sibling::details-menu")
    private WebElement creationNewEntityMenu;

    @FindBy(linkText = "New repository")
    private WebElement newRepositoryMenuItemLink;

    @FindBy(linkText = "New organization")
    private WebElement newOrganizationMenuItemLink;

    @FindBy(linkText = "New gist")
    private WebElement newGistMenuItemLink;

    @Inject
    public CreationNewEntityMenu(TestContextManager driverManager) {
        super(driverManager);
    }

    public void waitForCreationNewEntityMenuBtn() {
        wait.until(ExpectedConditions.visibilityOf(creationNewEntityMenuBtn));
    }

    public void openCreationNewEntityMenu() {
        creationNewEntityMenuBtn.click();
    }

    public void waitForCreationNewEntityMenu() {
        wait.until(ExpectedConditions.visibilityOf(creationNewEntityMenu));
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
