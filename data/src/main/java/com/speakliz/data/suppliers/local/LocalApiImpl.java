package com.speakliz.data.suppliers.local;


import com.speakliz.data.entity.PostEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Implementation of {@link LocalApi} data is created locally in memory
 */
public class LocalApiImpl implements LocalApi{

    private List<PostEntity> entityList;

    /**
     * Data is created in constructor
     */
    public LocalApiImpl() {
        entityList=  new ArrayList<>();
        for (int i=0; i<10; i++){
            PostEntity postEntity = new PostEntity();
            postEntity.setPostId(i);
            entityList.add(postEntity);
        }
    }

    @Override
    public Observable<List<PostEntity>> postEntityList() {
        return Observable.create(emitter -> {
            emitter.onNext(entityList);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<PostEntity> postEntityById(int postId) {
        return Observable.create(emitter -> {
            emitter.onNext(entityList.get(postId));
            emitter.onComplete();
        });
    }
}
