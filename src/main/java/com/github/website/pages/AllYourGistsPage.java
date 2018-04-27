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

    @FindBy(css = "span.creator > a > strong.css-truncate-target")
    private List<WebElement> gistItem;

    @Inject
    public AllYourGistsPage(TestContextManager driverManager) {
        super(driverManager);
    }

    public void waitForAllYourGistsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='repository-content gist-content']")));
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
