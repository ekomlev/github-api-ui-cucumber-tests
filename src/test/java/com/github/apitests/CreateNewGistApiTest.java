package com.github.apitests;

import com.github.client.RestClient;
import com.github.entities.Gist;
import com.github.entities.User;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CreateNewGistApiTest extends BaseTest {
    private RestClient restClient;
    private Gist gist;

    @Inject
    @Named("Url")
    private String Url;

    @Inject
    @Named("gistsApiUrl")
    private String gistsApiUrl;

    @Inject
    private CreateNewGistApiTest(RestClient restClient, User user) {
        this.restClient = restClient;
        this.gist = user.getUserGist();
    }

    @Test
    public void createNewGist(ITestContext testName) {
        test(testName.getCurrentXmlTest().getName(), "started");

        Gson gson = new Gson();
        String jsonInString = gson.toJson(gist);

        CloseableHttpResponse closeableHttpResponse = restClient.post(Url + gistsApiUrl, jsonInString);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201,
                "Status code is not 201: is not created.");

        resultLoggerInfo(closeableHttpResponse);
        test(testName.getCurrentXmlTest().getName(), "completed");
    }
}
