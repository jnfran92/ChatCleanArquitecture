package com.speakliz.data.suppliers.net;

import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class ApiConnectionTest {

    private ApiConnection apiConnection;

    private static final String URL_TO_TEST = "https://jsonplaceholder.typicode.com/posts";


    @Before
    public void setUp() throws MalformedURLException {
        apiConnection = ApiConnection.createGET(URL_TO_TEST);
    }

    @Test
    public void testGetResponseNotNull(){
        String response = apiConnection.requestSyncCall();
        assertNotNull(response);
    }
}