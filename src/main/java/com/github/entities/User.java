package com.github.entities;

public class User {
    private String userName;
    private String userEmail;
    private String userPassword;
    private PublicProfile userPublicProfile;
    private Repository userRepository;
    private Organization userOrganization;
    private Gist userGist;
    private Comment userComment;

    private User(String userName, String userEmail, String userPassword, PublicProfile userPublicProfile,
                 Repository userRepository, Organization userOrganization, Gist userGist, Comment userComment) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPublicProfile = userPublicProfile;
        this.userRepository = userRepository;
        this.userOrganization = userOrganization;
        this.userGist = userGist;
        this.userComment = userComment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public PublicProfile getUserPublicProfile() {
        return userPublicProfile;
    }

    public void setUserPublicProfile(PublicProfile userPublicProfile) {
        this.userPublicProfile = userPublicProfile;
    }

    public Repository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(Repository userRepository) {
        this.userRepository = userRepository;
    }

    public Organization getUserOrganization() {
        return userOrganization;
    }

    public void setUserOrganization(Organization userOrganization) {
        this.userOrganization = userOrganization;
    }

    public Gist getUserGist() {
        return userGist;
    }

    public void setUserGist(Gist userGist) {
        this.userGist = userGist;
    }

    public Comment getUserComment() {
        return userComment;
    }

    public void setUserComment(Comment userComment) {
        this.userComment = userComment;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPublicProfile=" + userPublicProfile +
                ", userRepository=" + userRepository +
                ", userOrganization=" + userOrganization +
                ", userGist=" + userGist +
                ", userComment=" + userComment +
                '}';
    }

}
