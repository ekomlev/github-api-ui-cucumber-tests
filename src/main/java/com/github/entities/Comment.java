package com.github.entities;

public class Comment {
    private String commentText;

    public Comment(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentText='" + commentText + '\'' +
                '}';
    }
}
