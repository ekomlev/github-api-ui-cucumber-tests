package com.github.website.pages;

import com.github.website.base.BasePage;
import com.github.base.driver.TestContextManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfileSettingsPage extends BasePage {

    @FindBy(id = "user_profile_name")
    private WebElement inputPublicProfileNameField;

    @FindBy(id = "user_profile_bio")
    private WebElement inputPublicProfileBioField;

    @FindBy(id = "user_profile_blog")
    private WebElement inputPublicProfileUrlField;

    @FindBy(id = "user_profile_company")
    private WebElement inputPublicProfileCompanyField;

    @FindBy(id = "user_profile_location")
    private WebElement inputPublicProfileLocationField;

    @FindBy(xpath = "//button[@type='submit' and text()='Update profile']")
    private WebElement updateProfileButton;

    @FindBy(linkText = "view your profile.")
    private WebElement viewProfileLink;

    @Inject
    public ProfileSettingsPage(TestContextManager driverManager) {
        super(driverManager);
    }

    public void waitForPublicProfileForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='profile_36765220']")));
    }

    public void savePublicProfileSettings() {
        updateProfileButton.click();
    }

    public void changeProfileName(String publicProfileName) {
        inputPublicProfileNameField.clear();
        inputPublicProfileNameField.sendKeys(publicProfileName);
    }

    public void changeProfileBio(String publicProfileBio) {
        inputPublicProfileBioField.clear();
        inputPublicProfileBioField.sendKeys(publicProfileBio);
    }

    public void changeProfileUrl(String publicProfileUrl) {
        inputPublicProfileUrlField.clear();
        inputPublicProfileUrlField.sendKeys(publicProfileUrl);
    }

    public void changeProfileCompany(String publicProfileCompany) {
        inputPublicProfileCompanyField.clear();
        inputPublicProfileCompanyField.sendKeys(publicProfileCompany);
    }

    public void changeProfileLocation(String publicProfileLocation) {
        inputPublicProfileLocationField.clear();
        inputPublicProfileLocationField.sendKeys(publicProfileLocation);
    }

    public void waitForValidationMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("view your profile.")));
    }

    public void openProfilePage() {
        viewProfileLink.click();
    }
}
