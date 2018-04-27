package com.github.apitests;

import com.github.base.injector.ManagerModule;
import com.github.logging.LoggerInstanceProvider;
import com.github.utils.RestClient;
import com.google.inject.Inject;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Guice;

import java.io.IOException;

@Guice(modules = ManagerModule.class)
class BaseTest {
    private Logger logger = LoggerInstanceProvider.getLogger(BaseTest.class);
    int RESPONSE_STATUS_CODE_200 = 200;
    int RESPONSE_STATUS_CODE_500 = 500;
    int RESPONSE_STATUS_CODE_400 = 400;
    int RESPONSE_STATUS_CODE_401 = 401;
    int RESPONSE_STATUS_CODE_201 = 201;
    int RESPONSE_STATUS_CODE_204 = 204;
    @Inject
    private RestClient rest;


    void resultLoggerInfo(CloseableHttpResponse closeableHttpResponse) {
        String responseString = null;
        try {
            responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject responseJson = new JSONObject(responseString);
        resultInfo("Response JSON from API ---> " + responseJson);

        for (Header header : closeableHttpResponse.getAllHeaders()) {
            resultInfo(header.getName() + " : " + header.getValue());
        }

        resultInfo("Status line: " + closeableHttpResponse.getStatusLine().toString());

    }

    void test(String testName, String msg) {
        logger.info(String.format("====================Test '%1$s' %2$s =======================", testName, msg));
    }

    void resultInfo(String msg) {
        logger.info(msg);
    }
}
