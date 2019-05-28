package com.juanchango.data.repository.datasource;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.suppliers.cache.PostCache;
import com.juanchango.data.suppliers.local.LocalApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link PostDataStore} implementation for disk retrieve data
 */

public class DiskPostDataStore implements PostDataStore {

    private final PostCache postCache;

    DiskPostDataStore(PostCache postCache) {
        this.postCache = postCache;
    }

    @Override
    public Observable<List<PostEntity>> postEntityList() {
        throw new UnsupportedOperationException("Operation is not available");
    }

    @Override
    public Observable<PostEntity> postEntityDetails(int postId) {
        return this.postCache.get(postId);
    }
}
