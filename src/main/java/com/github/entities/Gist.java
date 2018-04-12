package com.github.entities;

public class Gist {
    private String gistFile;
    private String gistDescription;
    private String gistContent;
    private boolean gistPublicAccess;

    public Gist(String gistFile, String gistDescription, String gistContent, boolean gistPublicAccess) {
        this.gistFile = gistFile;
        this.gistDescription = gistDescription;
        this.gistContent = gistContent;
        this.gistPublicAccess = gistPublicAccess;
    }

    public String getGistFile() {
        return gistFile;
    }

    public void setGistFile(String gistFile) {
        this.gistFile = gistFile;
    }

    public String getGistDescription() {
        return gistDescription;
    }

    public void setGistDescription(String gistDescription) {
        this.gistDescription = gistDescription;
    }

    public String getGistContent() {
        return gistContent;
    }

    public void setGistContent(String gistContent) {
        this.gistContent = gistContent;
    }

    public boolean getGistPublicAccess() {
        return gistPublicAccess;
    }

    public void setGistPublicAccess(boolean gistPublicAccess) {
        this.gistPublicAccess = gistPublicAccess;
    }

    @Override
    public String toString() {
        return "Gist{" +
                "gistFile='" + gistFile + '\'' +
                ", gistContent='" + gistContent + '\'' +
                ", gistDescription='" + gistDescription + '\'' +
                ", gistPublicAccess=" + gistPublicAccess +
                '}';
    }
}
