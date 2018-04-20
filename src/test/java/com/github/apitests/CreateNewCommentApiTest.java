package com.github.apitests;

import com.github.client.RestClient;
import com.github.entities.Comment;
import com.github.entities.User;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CreateNewCommentApiTest extends BaseTest {
    private RestClient restClient;
    private Comment comment;

    @Inject
    @Named("Url")
    private String Url;

    @Inject
    @Named("gistsApiUrl")
    private String gistsApiUrl;

    @Inject
    @Named("gistId")
    private String gistsId;

    @Inject
    @Named("commentApiUrl")
    private String commentApiUrl;

    @Inject
    private CreateNewCommentApiTest(RestClient rest, User user) {
        this.restClient = rest;
        this.comment = user.getUserComment();
    }

    @Test
    public void createNewComment(ITestContext testName) {
        test(testName.getCurrentXmlTest().getName(), "started");

        if (restClient.get(Url + gistsApiUrl + gistsId).getStatusLine().getStatusCode() == RESPONSE_STATUS_CODE_200) {
            Gson gson = new Gson();
            String jsonInString = gson.toJson(comment);
            CloseableHttpResponse closeableHttpResponse = restClient.post(Url + gistsApiUrl + gistsId + commentApiUrl, jsonInString);

            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

            Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201,
                    "Status code is not 201: gist is not created.");

            resultLoggerInfo(closeableHttpResponse);
        } else {
            System.out.println("Gist #" + gistsId + " is not found");
            System.out.println(restClient.get(Url + gistsApiUrl + gistsId).getStatusLine().getStatusCode());
        }

        test(testName.getCurrentXmlTest().getName(), "completed");
    }
}
