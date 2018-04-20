package com.github.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Gist {
    @SerializedName("description")
    private String gistDescription;
    @SerializedName("public")
    private boolean gistPublicAccess;
    @SerializedName("files")
    private Map<String, Map<String, String>> gistFile;

    public Gist(String gistDescription, boolean gistPublicAccess, Map<String, Map<String, String>> gistFile) {
        this.gistDescription = gistDescription;
        this.gistPublicAccess = gistPublicAccess;
        this.gistFile = gistFile;
    }

    public Map<String, Map<String, String>> getGistFile() {
        return gistFile;
    }

    public void setGistFile(Map<String, Map<String, String>> gistFile) {
        this.gistFile = gistFile;
    }

    public String getGistDescription() {
        return gistDescription;
    }

    public void setGistDescription(String gistDescription) {
        this.gistDescription = gistDescription;
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
                "description='" + gistDescription + '\'' +
                ", publik=" + gistPublicAccess +
                ", files=" + gistFile +
                '}';
    }
}
