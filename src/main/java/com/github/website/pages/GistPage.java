package com.github.website.pages;

import com.github.base.page.BasePage;
import com.github.base.driver.DriverManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GistPage extends BasePage {

    @FindBy(xpath = "//h1[@class='public css-truncate']/strong[@itemprop='name']/a")
    private WebElement gistNameLink;

    @FindBy(xpath = "//textarea[@id='new_comment_field']")
    private WebElement gistCommentInputfield;

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Comment')]")
    private WebElement gistCommentButton;

    @FindBy(xpath = "//div[@class='timeline-comment-wrapper js-comment-container' and position()=last()-2]")
    private WebElement lastGistComment;

    @Inject
    public GistPage(DriverManager driverManager) {
        super(driverManager);
    }

    public void waitForGistHeadOfGistPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='public css-truncate']")));
    }

    public void waitForGistCommentForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@class='js-new-comment-form js-needs-timeline-marker-header']")));
    }

    public void waitForGistComment() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='timeline-comment-wrapper js-comment-container' and position()=last()-2]")));
    }

    public void waitForGistContent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container new-discussion-timeline experiment-repo-nav']")));
    }

    public String getNameOfOpenedGist() {
        return gistNameLink.getText();
    }

    public void saveNewGistComment(String commentText) {
        gistCommentInputfield.sendKeys(commentText);
        gistCommentButton.click();
    }

    public String getLastCommentText() {
        return lastGistComment.getText();
    }
}
