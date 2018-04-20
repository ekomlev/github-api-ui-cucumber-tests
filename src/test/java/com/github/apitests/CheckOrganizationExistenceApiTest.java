package com.github.apitests;

import com.github.client.RestClient;
import com.github.entities.Organization;
import com.github.entities.User;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CheckOrganizationExistenceApiTest extends BaseTest {
    private RestClient restClient;
    private Organization organization;

    @Inject
    @Named("Url")
    private String Url;

    @Inject
    @Named("organizationApiUrl")
    private String organizationApiUrl;

    @Inject
    private CheckOrganizationExistenceApiTest(RestClient rest, User user) {
        this.restClient = rest;
        this.organization = user.getUserOrganization();
    }

    @Test
    public void checkOrganization(ITestContext testName) {
        test(testName.getCurrentXmlTest().getName(), "started");

        String organizationName = organization.getOrganizationName();

        CloseableHttpResponse closeableHttpResponse = restClient.get(Url + organizationApiUrl + "/" + organizationName);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,
                "Status code is not 200: organization is not exist.");

        resultLoggerInfo(closeableHttpResponse);
        test(testName.getCurrentXmlTest().getName(), "completed");

    }
}
