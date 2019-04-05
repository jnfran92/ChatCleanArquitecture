package com.speakliz.data.repository.datasource;

import com.speakliz.data.entity.PostEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link PostDataStore} implementation for disk retrieve data
 */

public class DiskPostDataStore implements PostDataStore {



    @Override
    public Observable<List<PostEntity>> postEntityList() {
        return null;
    }

    @Override
    public Observable<PostEntity> postEntityDetails() {
        return null;
    }
}
