package com.github.entities;

import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("name")
    private String repositoryName;

    @SerializedName("description")
    private String repositoryDescription;

    @SerializedName("private")
    private boolean repositoryPrivateAccess;

    @SerializedName("has_issues")
    private boolean repositoryIssuesExistence;

    @SerializedName("has_projects")
    private boolean repositoryProjectsExistence;

    @SerializedName("has_wiki")
    private boolean repositoryWikiExistence;

    public Repository(String repositoryName, String repositoryDescription, boolean repositoryPrivateAccess,
                      boolean repositoryIssuesExistence, boolean repositoryProjectsExistence,
                      boolean repositoryWikiExistence) {
        this.repositoryName = repositoryName;
        this.repositoryDescription = repositoryDescription;
        this.repositoryPrivateAccess = repositoryPrivateAccess;
        this.repositoryIssuesExistence = repositoryIssuesExistence;
        this.repositoryProjectsExistence = repositoryProjectsExistence;
        this.repositoryWikiExistence = repositoryWikiExistence;
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

    public boolean isRepositoryPrivateAccess() {
        return repositoryPrivateAccess;
    }

    public void setRepositoryPrivateAccess(boolean repositoryPrivateAccess) {
        this.repositoryPrivateAccess = repositoryPrivateAccess;
    }

    public boolean isRepositoryIssuesExistence() {
        return repositoryIssuesExistence;
    }

    public void setRepositoryIssuesExistence(boolean repositoryIssuesExistence) {
        this.repositoryIssuesExistence = repositoryIssuesExistence;
    }

    public boolean isRepositoryProjectsExistence() {
        return repositoryProjectsExistence;
    }

    public void setRepositoryProjectsExistence(boolean repositoryProjectsExistence) {
        this.repositoryProjectsExistence = repositoryProjectsExistence;
    }

    public boolean isRepositoryWikiExistence() {
        return repositoryWikiExistence;
    }

    public void setRepositoryWikiExistence(boolean repositoryWikiExistence) {
        this.repositoryWikiExistence = repositoryWikiExistence;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "repositoryName='" + repositoryName + '\'' +
                ", repositoryDescription='" + repositoryDescription + '\'' +
                ", repositoryPrivateAccess=" + repositoryPrivateAccess +
                ", repositoryIssuesExistence=" + repositoryIssuesExistence +
                ", repositoryProjectsExistence=" + repositoryProjectsExistence +
                ", repositoryWikiExistence=" + repositoryWikiExistence +
                '}';
    }
}
