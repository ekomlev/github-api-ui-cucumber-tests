package com.github.apitests;

import com.github.client.RestClient;
import com.github.entities.PublicProfile;
import com.github.entities.User;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class UpdateProfileSettingsApiTest extends BaseTest {
    private RestClient restClient;
    private PublicProfile userPublicProfile;

    @Inject
    @Named("Url")
    private String Url;

    @Inject
    @Named("userApiUrl")
    private String userApiUrl;

    @Inject
    private UpdateProfileSettingsApiTest(RestClient restClient, User user) {
        this.restClient = restClient;
        this.userPublicProfile = user.getUserPublicProfile();
    }

    @Test
    public void createNewRepository(ITestContext testName) {
        test(testName.getCurrentXmlTest().getName(), "started");

        Gson gson = new Gson();
        String jsonInString = gson.toJson(userPublicProfile);
        CloseableHttpResponse closeableHttpResponse = restClient.patch(Url + userApiUrl, jsonInString);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,
                "Status code is not 200: profile settings are not updated.");

        resultLoggerInfo(closeableHttpResponse);
        test(testName.getCurrentXmlTest().getName(), "completed");
    }
}
