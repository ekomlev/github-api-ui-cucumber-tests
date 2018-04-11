package com.github.base.driver;

import com.github.utils.WebProvider;
import com.github.website.GithubSite;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private WebDriver webDriver;
    private WebProvider webProvider;
    private GithubSite github;
    private String githubUrl;

    @Inject
    private Provider<GithubSite> githubSiteProvider;

    @Inject
    public DriverManager(WebDriver webDriver, WebProvider webProvider, GithubSite github, @Named("environment.variables.base_url") String githubUrl) {
        this.webDriver = webDriver;
        this.webProvider = webProvider;
        this.github = github;
        this.githubUrl = githubUrl;
    }

    public WebDriver getDriver() {
        if (webDriver == null) {
            this.webDriver = webProvider.get();
        }
        return webDriver;
    }

    public GithubSite getGithub() {
        if (github == null) {
            this.github = githubSiteProvider.get();
        }
        return github;
    }

    public void open() {
        getGithub();
        getDriver().navigate().to(githubUrl);
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void reset() {
        webDriver.quit();
        webDriver = null;
        github = null;
    }
}
