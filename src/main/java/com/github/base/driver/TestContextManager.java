package com.github.base.driver;

import com.github.base.injector.WebDriverProvider;
import com.github.website.GithubSite;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;

public class TestContextManager {
    private WebDriver webDriver;
    private WebDriverProvider webDriverProvider;
    private GithubSite github;
    private String githubUrl;

    @Inject
    private Provider<GithubSite> githubSiteProvider;

    @Inject
    public TestContextManager(WebDriver webDriver, WebDriverProvider webDriverProvider, GithubSite github, @Named("environment.variables.base_url") String githubUrl) {
        this.webDriver = webDriver;
        this.webDriverProvider = webDriverProvider;
        this.github = github;
        this.githubUrl = githubUrl;
    }

    public WebDriver getDriver() {
        if (webDriver == null) {
            this.webDriver = webDriverProvider.get();
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
