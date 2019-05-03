package com.speakliz.data.repository.datasource;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.data.suppliers.net.RestApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link PostDataStore} implementation based on connection to the api (Cloud).
 */
public class CloudPostDataStore implements PostDataStore {

    private final RestApi restApi;

    public CloudPostDataStore(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<PostEntity>> postEntityList() {
        return restApi.postEntityList();
    }

    @Override
    public Observable<PostEntity> postEntityDetails(int postId) {
        return restApi.postEntityById(postId);
    }
}
