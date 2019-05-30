package com.juanchango.data.repository.datasource;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.suppliers.cache.PostCache;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link PostDataSource} implementation for disk retrieve data
 */

public class DiskPostDataSource implements PostDataSource {

    private final PostCache postCache;

    DiskPostDataSource(PostCache postCache) {
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
