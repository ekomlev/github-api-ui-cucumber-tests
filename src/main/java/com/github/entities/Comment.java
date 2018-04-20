package com.github.entities;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("body")
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
