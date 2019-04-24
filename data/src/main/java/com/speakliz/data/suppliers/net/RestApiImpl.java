package com.speakliz.data.suppliers.net;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.data.suppliers.local.LocalApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * Class for Consume Rest API https://jsonplaceholder.typicode.com/posts
 * Implementation of {@link LocalApi}
 */

public class RestApiImpl implements RestApi {

    public RestApiImpl() {
    }

    @Override
    public Observable<List<PostEntity>> postEntityList() {
        return null;
    }

    @Override
    public Observable<PostEntity> postEntityById(int postId) {
        return null;
    }
}
