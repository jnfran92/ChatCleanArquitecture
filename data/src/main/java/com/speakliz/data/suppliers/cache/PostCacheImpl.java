package com.speakliz.data.suppliers.cache;

import com.speakliz.data.entity.PostEntity;

import io.reactivex.Observable;

public class PostCacheImpl implements PostCache {
    @Override
    public Observable<PostEntity> get(int postId) {
        return null;
    }

    @Override
    public void put(PostEntity postEntity) {

    }

    @Override
    public boolean isCached(int postId) {
        return false;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {

    }
}
