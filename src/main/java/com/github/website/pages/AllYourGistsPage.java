package com.github.website.pages;

import com.github.website.base.BasePage;
import com.github.base.driver.TestContextManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AllYourGistsPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'gist-snippet')]//a/strong[contains(@class, 'css-truncate-target')]")
    private List<WebElement> gistItem;
    @FindBy(xpath = "//*[@aria-label='All gists']/ancestor::div[contains(@class, 'col-9 col-md-9')]")
    private WebElement allGistColumn;

    @Inject
    public AllYourGistsPage(TestContextManager driverManager) {
        super(driverManager);
    }

    public void waitForAllYourGistsList() {
        wait.until(ExpectedConditions.visibilityOf(allGistColumn));
    }

    public WebElement gistIsExist(String gistFile) {
        for (WebElement gist : gistItem) {
            if (gist.getText().contains(gistFile)) {
                return gist;
            }
        }
        return null;
    }

    public void openGist(WebElement gistItem) {
        gistItem.click();
    }
}
