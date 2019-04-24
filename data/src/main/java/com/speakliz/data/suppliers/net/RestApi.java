package com.speakliz.data.suppliers.net;

import com.speakliz.data.entity.PostEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * RestAPI for retrieving data from the network
 */
public interface RestApi {

    /**
     * BASE URL REST API,  public url
     */
    String API_BASE_URL = "https://jsonplaceholder.typicode.com/";

    /**
     * API URL for getting all Post: List of {@link PostEntity}
     */
    String API_URL_GET_POST_LIST = API_BASE_URL + "posts";

    /**
     * API URL fro getting Post details: {@link PostEntity}
     * Concatenate the "postId" in the String
     */
    String API_URL_GET_POST_DETAILS = API_URL_GET_POST_LIST + "/";


    /**
     * Retrieve an Observable Type: List of {@link PostEntity}
     * @return Observable
     */
    Observable<List<PostEntity>> postEntityList();

    /**
     * Get Post details
     * @param postId
     * @return  Observable of {@link PostEntity}
     */
    Observable<PostEntity> postEntityById(int postId);
}
