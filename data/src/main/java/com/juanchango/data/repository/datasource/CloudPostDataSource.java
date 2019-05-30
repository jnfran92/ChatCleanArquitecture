package com.juanchango.data.repository.datasource;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.suppliers.cache.PostCache;
import com.juanchango.data.suppliers.net.RestApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link PostDataSource} implementation based on connection to the api (Cloud).
 */
public class CloudPostDataSource implements PostDataSource {

    private final RestApi restApi;
    private final PostCache postCache;

    CloudPostDataSource(RestApi restApi, PostCache postCache) {
        this.restApi = restApi;
        this.postCache = postCache;
    }

    @Override
    public Observable<List<PostEntity>> postEntityList() {
        return restApi.postEntityList();
    }

    @Override
    public Observable<PostEntity> postEntityDetails(final int postId) {
        return restApi.postEntityById(postId).doOnNext(CloudPostDataSource.this.postCache::put);
    }
}
