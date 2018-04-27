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

    private User() {
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
    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;
        User u = (User) obj;
        return (this.userName != null && this.userName.equals(u.userName) &&
                this.userEmail != null && this.userEmail.equals(u.userEmail) &&
                this.userPassword != null && this.userPassword.equals(u.userPassword) &&
                this.userPublicProfile != null && this.userPublicProfile == u.userPublicProfile &&
                this.userRepository != null && this.userRepository == u.userRepository &&
                this.userOrganization != null && this.userOrganization == u.userOrganization &&
                this.userGist != null && this.userGist == u.userGist &&
                this.userComment != null && this.userComment == u.userComment);
    }

    @Override
    public int hashCode() {
        return 31 * this.userName.hashCode() +
                16 * this.userEmail.hashCode() +
                15 * this.userPassword.hashCode() +
                14 * this.userPublicProfile.hashCode() +
                13 * this.userRepository.hashCode() +
                12 * this.userOrganization.hashCode() +
                11 * this.userGist.hashCode() +
                9 * this.userComment.hashCode();
    }

    @Override
    public String toString() {
        return "User {" +
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
