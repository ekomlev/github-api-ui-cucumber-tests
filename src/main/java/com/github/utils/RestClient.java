package com.github.client;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    @Inject
    @Named("apiToken")
    private String apiToken;

    public CloseableHttpResponse get(String Url) {
        HttpGet httpGet = new HttpGet(Url);
        return executeResponse(httpGet);
    }

    public CloseableHttpResponse post(String Url, String jsonInString) {
        HttpPost httpPost = new HttpPost(Url);
        setRequestEntity(httpPost, jsonInString);
        return executeResponse(httpPost);
    }

    public CloseableHttpResponse patch(String Url, String jsonInString) {
        HttpPatch httpPatch = new HttpPatch(Url);
        setRequestEntity(httpPatch, jsonInString);
        return executeResponse(httpPatch);
    }

    public CloseableHttpResponse delete(String Url) {
        HttpDelete httpDelete = new HttpDelete(Url);
        return executeResponse(httpDelete);
    }

    private void setRequestEntity(HttpEntityEnclosingRequestBase httpAction, String jsonInString) {
        try {
            httpAction.setEntity(new StringEntity(jsonInString));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private CloseableHttpResponse executeResponse(HttpRequestBase httpAction) {
        CloseableHttpResponse closeableHttpResponse = null;
        CloseableHttpClient closeableHttpClient = buildCloseableHttpClient();

        for (Map.Entry<String, String> entry : getHeaders().entrySet()) {
            httpAction.addHeader(entry.getKey(), entry.getValue());
        }
        try {
            closeableHttpResponse = closeableHttpClient.execute(httpAction);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return closeableHttpResponse;
    }

    private CloseableHttpClient buildCloseableHttpClient() {
        return HttpClientBuilder.create()
                .build();
    }

    private HashMap<String, String> getHeaders() {
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Authorization", "Bearer " + apiToken);
        return headerMap;
    }
}
