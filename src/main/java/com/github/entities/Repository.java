package com.github.entities;

public class Repository {
    String repositoryName;
    String repositoryDescription;
    boolean repositoryPublicAccess;

    public Repository(String repositoryName, String repositoryDescription, boolean repositoryPublicAccess) {
        this.repositoryName = repositoryName;
        this.repositoryDescription = repositoryDescription;
        this.repositoryPublicAccess = repositoryPublicAccess;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getRepositoryDescription() {
        return repositoryDescription;
    }

    public void setRepositoryDescription(String repositoryDescription) {
        this.repositoryDescription = repositoryDescription;
    }

    public boolean getRepositoryPublicAccess() {
        return repositoryPublicAccess;
    }

    public void setRepositoryPublicAccess(boolean repositoryPublicAccess) {
        this.repositoryPublicAccess = repositoryPublicAccess;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "repositoryName='" + repositoryName + '\'' +
                ", repositoryDescription='" + repositoryDescription + '\'' +
                ", repositoryPublicAccess=" + repositoryPublicAccess +
                '}';
    }
}
