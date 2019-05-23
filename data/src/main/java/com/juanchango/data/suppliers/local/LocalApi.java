package com.juanchango.data.suppliers.local;

import io.reactivex.Observable;

import com.juanchango.data.entity.PostEntity;

import java.util.List;

/**
 * LocalAPI which mimics the real behavior of a API (Data is created locally)
 */
public interface LocalApi {

    /**
     * Retrieve {@link PostEntity} List and single object
     * @return Observable
     */
    Observable<List<PostEntity>> postEntityList();
    Observable<PostEntity> postEntityById(int postId);
}
