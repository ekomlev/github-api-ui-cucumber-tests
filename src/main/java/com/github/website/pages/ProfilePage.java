package com.github.website.pages;

import com.github.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//h1[@class='vcard-names']/span[1]")
    WebElement actualPublicProfileNameField;

    @FindBy(xpath = "//div[@class='p-note user-profile-bio']/div")
    WebElement actualPublicProfileBioField;

    @FindBy(xpath = "//li[@itemprop='url']/a")
    WebElement actualPublicProfileUrlField;

    @FindBy(xpath = "//li[@itemprop='worksFor']/span[@class='p-org']/div")
    WebElement actualPublicProfileCompanyField;

    @FindBy(xpath = "//li[@itemprop='homeLocation']/span[@class='p-label']")
    WebElement actualPublicProfileLocationField;

    public ProfilePage(WebDriver driver) {
        super(driver);
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
