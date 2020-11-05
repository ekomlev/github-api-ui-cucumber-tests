package com.github.website.pages;

import com.github.base.driver.TestContextManager;
import com.github.website.base.BasePage;
import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewGistPage extends BasePage {

    @FindBy(css = "#gists")
    private WebElement newGistFrom;

    @FindBy(xpath = "//div[contains(@class, 'application-main')]/div[@itemscope]")
    private WebElement gistPageBody;

    @FindBy(xpath = "//div[@id='gists']/input[@name='gist[description]']")
    private WebElement gistDescriptionInputField;

    @FindBy(xpath = "//div[@id='gists']//input[@name='gist[contents][][name]']")
    private WebElement gistFileNameInputField;

    @FindBy(xpath = "//div[@class='CodeMirror-code']/div/pre[contains(@class, 'CodeMirror-line')]")
    private WebElement gistContentInputField;

    @FindBy(xpath = "//details[contains(@class, 'details-reset')]/summary[contains(@aria-label, 'Select a type of pull request')]")
    private WebElement expandTypeRequestMenuBtn;

    @FindBy(xpath = "//details[@open]/details-menu[contains(@class, 'select-menu-modal')]")
    private WebElement typePullRequestMenu;

    @FindBy(xpath = "//details[@open]//span[contains(text(), 'Create public gist')]/ancestor::label[@role='menuitemradio']")
    private WebElement publicItemTypePullRequestMenu;

    @FindBy(xpath = "//div[@class='BtnGroup']/button[@type='submit' and contains(text(), 'Create secret gist')]")
    private WebElement createSecretGistBtn;

    @FindBy(xpath = "//div[@class='BtnGroup']/button[@type='submit' and contains(text(), 'Create public gist')]")
    private WebElement createPublicGistBtn;

    @FindBy(xpath = "//li[@class='flex-auto py-3 text-bold text-right f6 lh-condensed']/a[contains(text(), 'See all of your gists')]")
    private WebElement allYourGistsPageLink;

    @Inject
    public NewGistPage(TestContextManager driverManager) {
        super(driverManager);
    }

    public void waitForNewGistForm() {
        wait.until(ExpectedConditions.visibilityOf(newGistFrom));
    }

    public void waitForGistPageBody() {
        wait.until(ExpectedConditions.visibilityOf(gistPageBody));
    }

    public void saveNewGist(String gistFile, String gistDescription, String gistContent, boolean gistPublicAccess) {
        gistDescriptionInputField.sendKeys(gistDescription);
        gistFileNameInputField.sendKeys(gistFile);

        Actions actions = new Actions(driverManager.getDriver());
        actions.moveToElement(gistContentInputField);
        actions.click();
        actions.sendKeys(gistContent);
        actions.build().perform();

        waitForCreateSecretGistButtonEnabled();

        if (gistPublicAccess) {
            choosePublicTypePullRequest();
            createPublicGistBtn.click();
        } else
            createSecretGistBtn.click();
    }

    private void waitForCreateSecretGistButtonEnabled() {
        createSecretGistBtn.isEnabled();
    }

    public void choosePublicTypePullRequest() {
        expandTypeRequestMenuBtn.click();
        waitForTypePullRequestMenu();
        publicItemTypePullRequestMenu.click();
        waitForCreatePublicGistButtonEnabled();
    }

    private void waitForCreatePublicGistButtonEnabled() {
        createPublicGistBtn.isEnabled();
    }

    private void waitForTypePullRequestMenu() {
        wait.until(ExpectedConditions.visibilityOf(typePullRequestMenu));
    }



    public void openAllYourGistsPage() {
        allYourGistsPageLink.click();
    }
}