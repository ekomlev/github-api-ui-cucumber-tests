package com.github.website.pages;

import com.github.website.base.BasePage;
import com.github.base.driver.TestContextManager;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GistPage extends BasePage {

    @FindBy(xpath = "//h1/strong[@itemprop='name']/a")
    private WebElement gistNameLink;

    @FindBy(xpath = "//form[@class='js-new-comment-form js-needs-timeline-marker-header']")
    private WebElement gistCommentForm;

    @FindBy(xpath = "//textarea[@id='new_comment_field']")
    private WebElement gistCommentInputField;

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Comment')]")
    private WebElement gistCommentBtn;

    @FindBy(xpath = "//div[contains(@class, 'TimelineItem js-comment-container') and position()=last()-1]")
    private WebElement lastGistComment;

    @FindBy(xpath = "//div[contains(@class, 'new-discussion-timeline')]/div[contains(@class, 'gist-content')]")
    private WebElement gistContent;

    @Inject
    public GistPage(TestContextManager driverManager) {
        super(driverManager);
    }

    public void waitForGistHeadOfGistPage() {
        wait.until(ExpectedConditions.visibilityOf(gistNameLink));
    }

    public void waitForGistCommentForm() {
        wait.until(ExpectedConditions.visibilityOf(gistCommentForm));
    }

    public void waitForGistComment() {
        wait.until(ExpectedConditions.visibilityOf(lastGistComment));
    }

    public void waitForGistContent() {
        wait.until(ExpectedConditions.visibilityOf(gistContent));
    }

    public String getNameOfOpenedGist() {
        return gistNameLink.getText();
    }

    public void saveNewGistComment(String commentText) {
        gistCommentInputField.sendKeys(commentText);
        gistCommentBtn.click();
    }

    public String getLastCommentText() {
        return lastGistComment.getText();
    }
}