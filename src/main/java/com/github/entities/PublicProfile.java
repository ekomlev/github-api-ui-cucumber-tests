package com.github.entities;

public class PublicProfile {
    private String publicProfileName;
    private String publicProfileBio;
    private String publicProfileCompany;
    private String publicProfileUrl;
    private String publicProfileLocation;

    public PublicProfile(String publicProfileName, String publicProfileBio, String publicProfileCompany, String publicProfileUrl, String publicProfileLocation) {
        this.publicProfileName = publicProfileName;
        this.publicProfileBio = publicProfileBio;
        this.publicProfileCompany = publicProfileCompany;
        this.publicProfileUrl = publicProfileUrl;
        this.publicProfileLocation = publicProfileLocation;
    }

    public String getPublicProfileName() {
        return publicProfileName;
    }

    public void setPublicProfileName(String publicProfileName) {
        this.publicProfileName = publicProfileName;
    }

    public String getPublicProfileBio() {
        return publicProfileBio;
    }

    public void setPublicProfileBio(String publicProfileBio) {
        this.publicProfileBio = publicProfileBio;
    }

    public String getPublicProfileCompany() {
        return publicProfileCompany;
    }

    public void setPublicProfileCompany(String publicProfileCompany) {
        this.publicProfileCompany = publicProfileCompany;
    }

    public String getPublicProfileUrl() {
        return publicProfileUrl;
    }

    public void setPublicProfileUrl(String publicProfileUrl) {
        this.publicProfileUrl = publicProfileUrl;
    }

    public String getPublicProfileLocation() {
        return publicProfileLocation;
    }

    public void setPublicProfileLocation(String publicProfileLocation) {
        this.publicProfileLocation = publicProfileLocation;
    }
}
