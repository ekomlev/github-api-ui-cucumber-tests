package com.github.entities;

import com.google.gson.annotations.SerializedName;

public class PublicProfile {
    @SerializedName("name")
    private String publicProfileName;

    @SerializedName("email")
    private String publicProfileEmail;

    @SerializedName("blog")
    private String publicProfileBlogUrl;

    @SerializedName("company")
    private String publicProfileCompany;

    @SerializedName("location")
    private String publicProfileLocation;

    @SerializedName("hireable")
    private boolean publicProfileHireableStatus;

    @SerializedName("bio")
    private String publicProfileBio;

    public PublicProfile(String publicProfileName, String publicProfileEmail, String publicProfileBlogUrl,
                         String publicProfileCompany, String publicProfileLocation, boolean publicProfileHireableStatus,
                         String publicProfileBio) {
        this.publicProfileName = publicProfileName;
        this.publicProfileEmail = publicProfileEmail;
        this.publicProfileBlogUrl = publicProfileBlogUrl;
        this.publicProfileCompany = publicProfileCompany;
        this.publicProfileLocation = publicProfileLocation;
        this.publicProfileHireableStatus = publicProfileHireableStatus;
        this.publicProfileBio = publicProfileBio;
    }

    public String getPublicProfileName() {
        return publicProfileName;
    }

    public void setPublicProfileName(String publicProfileName) {
        this.publicProfileName = publicProfileName;
    }

    public String isPublicProfileEmail() {
        return publicProfileEmail;
    }

    public void setPublicProfileEmail(String publicProfileEmail) {
        this.publicProfileEmail = publicProfileEmail;
    }

    public String getPublicProfileBlogUrl() {
        return publicProfileBlogUrl;
    }

    public void setPublicProfileBlogUrl(String publicProfileBlogUrl) {
        this.publicProfileBlogUrl = publicProfileBlogUrl;
    }

    public String getPublicProfileCompany() {
        return publicProfileCompany;
    }

    public void setPublicProfileCompany(String publicProfileCompany) {
        this.publicProfileCompany = publicProfileCompany;
    }

    public String getPublicProfileLocation() {
        return publicProfileLocation;
    }

    public void setPublicProfileLocation(String publicProfileLocation) {
        this.publicProfileLocation = publicProfileLocation;
    }

    public boolean isPublicProfileHireableStatus() {
        return publicProfileHireableStatus;
    }

    public void setPublicProfileHireableStatus(boolean publicProfileHireableStatus) {
        this.publicProfileHireableStatus = publicProfileHireableStatus;
    }

    public String getPublicProfileBio() {
        return publicProfileBio;
    }

    public void setPublicProfileBio(String publicProfileBio) {
        this.publicProfileBio = publicProfileBio;
    }

    @Override
    public String toString() {
        return "PublicProfile{" +
                "publicProfileName='" + publicProfileName + '\'' +
                ", publicProfileEmail='" + publicProfileEmail + '\'' +
                ", publicProfileBlogUrl='" + publicProfileBlogUrl + '\'' +
                ", publicProfileCompany='" + publicProfileCompany + '\'' +
                ", publicProfileLocation='" + publicProfileLocation + '\'' +
                ", publicProfileHireableStatus=" + publicProfileHireableStatus +
                ", publicProfileBio='" + publicProfileBio + '\'' +
                '}';
    }
}
