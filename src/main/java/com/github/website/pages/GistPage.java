package com.github.website.pages;

import com.github.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GistPage extends BasePage {

    @FindBy(xpath = "//h1[@class='public css-truncate']/strong[@itemprop='name']/a")
    WebElement gistNameLink;

    @FindBy(xpath = "//textarea[@id='new_comment_field']")
    WebElement gistCommentInputfield;

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Comment')]")
    WebElement gistCommentButton;

    @FindBy(xpath = "//div[@class='timeline-comment-wrapper js-comment-container' and position()=last()-2]")
    WebElement lastGistComment;

    public GistPage(WebDriver driver) {
        super(driver);
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

    public String getNameOfOpenedGist() {
        String gistName = gistNameLink.getText();
        return gistName;
    }

    public void saveNewGistComment(String commentText) {
        gistCommentInputfield.sendKeys(commentText);
        gistCommentButton.click();
    }

    public String getLastCommentText() {
        String lastGistCommentText = lastGistComment.getText();
        return lastGistCommentText;
    }

}
