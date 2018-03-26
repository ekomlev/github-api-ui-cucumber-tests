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
    WebElement submitUpdateProfileButton;

    @FindBy(linkText = "view your profile.")
    WebElement viewProfileLink;

    public ProfileSettingsPage(WebDriver driver) {
        super(driver);
    }

    public void waitForPublicProfileForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='profile_36765220']")));
    }

    public void saveNewPublicProfileSettings(String publicProfileName, String publicProfileBio, String publicProfileUrl,
                                             String publicProfileCompany, String publicProfileLocation) {
        inputPublicProfileNameField.clear();
        inputPublicProfileNameField.sendKeys(publicProfileName);
        inputPublicProfileBioField.clear();
        inputPublicProfileBioField.sendKeys(publicProfileBio);
        inputPublicProfileUrlField.clear();
        inputPublicProfileUrlField.sendKeys(publicProfileUrl);
        inputPublicProfileCompanyField.clear();
        inputPublicProfileCompanyField.sendKeys(publicProfileCompany);
        inputPublicProfileLocationField.clear();
        inputPublicProfileLocationField.sendKeys(publicProfileLocation);

        submitUpdateProfileButton.click();
    }

    public void waitForValidationMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("view your profile.")));
    }

    public void openProfilePage() {
        viewProfileLink.click();
    }

}
