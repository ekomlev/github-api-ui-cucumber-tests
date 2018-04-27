package com.github.apitests;

import com.github.utils.RestClient;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CheckServiceAvailabilityApiTest extends BaseTest {
    private RestClient restClient;

    @Inject
    @Named("Url")
    private String Url;

    @Inject
    private CheckServiceAvailabilityApiTest(RestClient restClient) {
        this.restClient = restClient;
    }

    @Test
    public void getAvailabilityApiTest(ITestContext testName) {
        test(testName.getCurrentXmlTest().getName(), "started");

        CloseableHttpResponse closeableHttpResponse = restClient.get(Url);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,
                "Status code is not 200: server is not available.");

        resultLoggerInfo(closeableHttpResponse);
        test(testName.getCurrentXmlTest().getName(), "completed");
    }
}
