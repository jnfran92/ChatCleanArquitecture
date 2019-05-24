package com.juanchango.data.repository.datasource;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.suppliers.local.LocalApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link PostDataStore} implementation for disk retrieve data
 */

public class DiskPostDataStore implements PostDataStore {

    private final LocalApi localApi;

    DiskPostDataStore(LocalApi localApi) {
        this.localApi = localApi;
    }

    @Override
    public Observable<List<PostEntity>> postEntityList() {
        return this.localApi.postEntityList();
    }

    @Override
    public Observable<PostEntity> postEntityDetails(int id) {
        return this.localApi.postEntityById(id);
    }
}