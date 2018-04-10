package com.github.base.browser;

import com.github.website.GithubSite;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class DriverManager {
    private WebDriver webDriver; //instance for 1 scenario
    private WebProvider webProvider;
    private GithubSite github;
    private String githubUrl;

    @Inject
    public DriverManager(WebDriver webDriver, WebProvider webProvider, GithubSite github, @Named("environment.variables.base_url") String githubUrl) throws MalformedURLException {
        this.webDriver = webDriver;
        this.webProvider = webProvider;
        this.github = github;
        this.githubUrl = githubUrl;
    }

    /*public Map getDriver() {
        Map<String, Object> mapEntity = new HashMap<String, Object>();
        if (webDriver == null && github == null) {
            WebDriver webDriver  = webProvider.get();
            this.webDriver = webDriver;
            GithubSite github = new GithubSite();
            mapEntity.put("webDriver", webDriver);
            mapEntity.put("github", github);
        }
        else {
            mapEntity.put("webDriver", this.webDriver);
            mapEntity.put("github", this.github);
        }
            return mapEntity;

    }*/


    public WebDriver getDriver() {
        if (webDriver == null) {
            WebDriver webDriver = webProvider.get();
            this.webDriver = webDriver;
        }
        return webDriver;
    }

    public GithubSite getGithub() {
        if (github == null) {
            GithubSite github = new GithubSite();
            this.github = github;
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
