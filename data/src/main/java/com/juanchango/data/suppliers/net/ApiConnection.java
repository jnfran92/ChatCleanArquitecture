package com.juanchango.data.suppliers.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * API Connection using OkHTTP Android Library
 * @apiNote Only GET implemented
 */
public class ApiConnection implements Callable<String> {

    /**
     * HTTP format constants
     */
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";


    private URL url;
    private String response;

    private ApiConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    /**
     * Sort of Singleton for the API Connection
     * @param url Json REST URL
     * @return self class: {@link ApiConnection}
     * @throws MalformedURLException from {@link URL}
     */
    static ApiConnection createGET(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }

    String requestSyncCall(){
        connectToApi();
        return response;
    }

    private void connectToApi(){
        OkHttpClient okHttpClient = this.createClient();
        final Request request = new Request.Builder()
                .url(this.url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();

        try {
            this.response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient createClient(){
        final OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient;
    }


    @Override
    public String call() throws Exception {
        return requestSyncCall();
    }
}
