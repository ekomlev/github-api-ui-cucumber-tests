package com.github.apitests;

import com.github.client.RestClient;
import com.github.entities.Repository;
import com.github.entities.User;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CreateNewRepositoryApiTest extends BaseTest {
    private RestClient restClient;
    private Repository repository;
    private String ownerName;

    @Inject
    @Named("Url")
    private String Url;

    @Inject
    @Named("repositoryApiUrl")
    private String repositoryApiUrl;

    @Inject
    @Named("userApiUrl")
    private String userApiUrl;

    @Inject
    private CreateNewRepositoryApiTest(RestClient restClient, User user) {
        this.restClient = restClient;
        this.repository = user.getUserRepository();
        this.ownerName = user.getUserName();
    }

    @Test
    public void createNewRepository(ITestContext testName) {
        test(testName.getCurrentXmlTest().getName(), "started");

        if (checkRepositoryIsExist()) {
            deleteExistingRepository();
        }
        Gson gson = new Gson();
        String jsonInString = gson.toJson(repository);
        CloseableHttpResponse closeableHttpResponse = restClient.post(Url + userApiUrl + repositoryApiUrl, jsonInString);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201,
                "Status code is not 201: repository is not created.");

        resultLoggerInfo(closeableHttpResponse);
        test(testName.getCurrentXmlTest().getName(), "completed");
    }

    private boolean checkRepositoryIsExist() {
        CloseableHttpResponse closeableHttpResponse = restClient.get(getRepositoryUrl());
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        resultInfo("Repository is already exist. Response status code ---> " + statusCode);

        return statusCode == RESPONSE_STATUS_CODE_200;
    }

    private void deleteExistingRepository() {
        CloseableHttpResponse closeableHttpResponse = restClient.delete(getRepositoryUrl());
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        resultInfo("Existing repository is deleted. Response status code ---> " + statusCode);

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_204,
                "Status code is not 204: repository is not deleted.");
    }

    private String getRepositoryUrl() {
        return Url + repositoryApiUrl + "/" + ownerName + "/" + repository.getRepositoryName();
    }
}
