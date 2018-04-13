package com.github.website.pages;

import com.github.website.base.BasePage;
import com.github.base.driver.DriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewGistPage extends BasePage {

    @FindBy(xpath = "//div[@id='gists']/input[@name='gist[description]']")
    private WebElement gistDescriptionInputfield;

    @FindBy(xpath = "//div[@id='gists']//input[@name='gist[contents][][name]']")
    private WebElement gistFileNameInputfield;

    @FindBy(xpath = "//div[@class='CodeMirror-code']/div/pre[@class=' CodeMirror-line ']")
    private WebElement gistContentInputfield;

    @FindBy(xpath = "//div[@class='form-actions']/button[@type='submit' and contains(text(), 'Create secret gist')]")
    private WebElement createSecretGistButton;

    @FindBy(xpath = "//div[@class='form-actions']/button[@type='submit' and contains(text(), 'Create public gist')]")
    private WebElement createPublicGistButton;

    @FindBy(xpath = "//li[@class='flex-auto py-3 text-bold text-right f6 lh-condensed']/a[contains(text(), 'See all of your gists')]")
    private WebElement allYourGistsPageLink;

    @Inject
    public NewGistPage(DriverManager driverManager) {
        super(driverManager);
    }

    public void waitForNewGistForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gists")));
    }

    public void waitForGistHeadOfNewGistPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container repohead-details-container']")));
    }

    public void saveNewGist(String gistFile, String gistDescription, String gistContent, boolean gistPublicAccess) {
        gistDescriptionInputfield.sendKeys(gistDescription);
        gistFileNameInputfield.sendKeys(gistFile);

        Actions actions = new Actions(driverManager.getDriver());
        actions.moveToElement(gistContentInputfield);
        actions.click();
        actions.sendKeys(gistContent);
        actions.build().perform();

        waitForCreateSecretGistButtonEnabled();
        waitForCreatePublicGistButtonEnabled();

        if (gistPublicAccess) {
            createPublicGistButton.click();
        } else createSecretGistButton.click();
    }

    private void waitForCreateSecretGistButtonEnabled() {
        createSecretGistButton.isEnabled();
    }

    private void waitForCreatePublicGistButtonEnabled() {
        createPublicGistButton.isEnabled();
    }

    public void openAllYourGistsPage() {
        allYourGistsPageLink.click();
    }
}
