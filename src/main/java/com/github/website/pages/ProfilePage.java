package com.github.website.pages;

import com.github.base.BasePage;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//h1[@class='vcard-names']/span[1]")
    private WebElement actualPublicProfileNameField;

    @FindBy(xpath = "//div[@class='p-note user-profile-bio']/div")
    private WebElement actualPublicProfileBioField;

    @FindBy(xpath = "//li[@itemprop='url']/a")
    private WebElement actualPublicProfileUrlField;

    @FindBy(xpath = "//li[@itemprop='worksFor']/span[@class='p-org']/div")
    private WebElement actualPublicProfileCompanyField;

    @FindBy(xpath = "//li[@itemprop='homeLocation']/span[@class='p-label']")
    private WebElement actualPublicProfileLocationField;

    @Inject
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void waitForUserProfileDataColumn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='h-card col-3 float-left pr-3']")));
    }


    public String readActualPublicProfileNameField() {
       String actualPublicProfileName = actualPublicProfileNameField.getText();
       return actualPublicProfileName;
    }

    public String readActualPublicProfileBioField() {
        String actualPublicProfileBio = actualPublicProfileBioField.getText();
        return actualPublicProfileBio;
    }

    public String readActualPublicProfileUrlField() {
        String actualPublicProfileUrl = actualPublicProfileUrlField.getText();
        return actualPublicProfileUrl;
    }

    public String readActualPublicProfileCompanyField() {
        String actualPublicProfileCompany = actualPublicProfileCompanyField.getText();
        return actualPublicProfileCompany;
    }

    public String readActualPublicProfileLocationField() {
        String actualPublicProfileLocation = actualPublicProfileLocationField.getText();
        return actualPublicProfileLocation;
    }

}
