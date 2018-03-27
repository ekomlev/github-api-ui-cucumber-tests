package com.github.website.pages;

import com.github.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfileSettingsPage extends BasePage {

    @FindBy(id = "user_profile_name")
    WebElement inputPublicProfileNameField;

    @FindBy(id = "user_profile_bio")
    WebElement inputPublicProfileBioField;

    @FindBy(id = "user_profile_blog")
    WebElement inputPublicProfileUrlField;

    @FindBy(id = "user_profile_company")
    WebElement inputPublicProfileCompanyField;

    @FindBy(id = "user_profile_location")
    WebElement inputPublicProfileLocationField;

    @FindBy(xpath = "//button[@type='submit' and text()='Update profile']")
    WebElement updateProfileButton;

    @FindBy(linkText = "view your profile.")
    WebElement viewProfileLink;

    public ProfileSettingsPage(WebDriver driver) {
        super(driver);
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

    public void changeProfilebBio(String publicProfileBio) {
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
