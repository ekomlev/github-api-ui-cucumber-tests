package com.github.website.pages;

import com.github.website.base.BasePage;
import com.github.base.driver.DriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//h1[@class='vcard-names']/span[1]")
    private WebElement actualPublicProfileNameField;

    @FindBy(xpath = "//div[@class='d-inline-block mb-3 js-user-profile-bio-contents']/div")
    private WebElement actualPublicProfileBioField;

    @FindBy(xpath = "//li[@itemprop='url']/a")
    private WebElement actualPublicProfileUrlField;

    @FindBy(xpath = "//li[@itemprop='worksFor']/span[@class='p-org']/div")
    private WebElement actualPublicProfileCompanyField;

    @FindBy(xpath = "//li[@itemprop='homeLocation']/span[@class='p-label']")
    private WebElement actualPublicProfileLocationField;

    @Inject
    public ProfilePage(DriverManager driverManager) {
        super(driverManager);
    }

    public void waitForUserProfileDataColumn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='h-card col-3 float-left pr-3']")));
    }

    public String readActualPublicProfileNameField() {
        return actualPublicProfileNameField.getText();
    }

    public String readActualPublicProfileBioField() {
        return actualPublicProfileBioField.getText();
    }

    public String readActualPublicProfileUrlField() {
        return actualPublicProfileUrlField.getText();
    }

    public String readActualPublicProfileCompanyField() {
        return actualPublicProfileCompanyField.getText();
    }

    public String readActualPublicProfileLocationField() {
        return actualPublicProfileLocationField.getText();
    }
}
