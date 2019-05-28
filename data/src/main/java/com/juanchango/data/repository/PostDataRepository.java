package com.juanchango.data.repository;

import com.juanchango.data.entity.mapper.PostEntityDataMapper;
import com.juanchango.data.repository.datasource.PostDataStore;
import com.juanchango.data.repository.datasource.PostDataStoreFactory;
import com.juanchango.domain.model.Post;
import com.juanchango.domain.repository.PostRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * The interface between data Layer and domain Layer
 * Singleton
 */
@Singleton
public class PostDataRepository implements PostRepository {

    private final PostDataStoreFactory postDataStoreFactory;
    private final PostEntityDataMapper postEntityDataMapper;

    @Inject
    PostDataRepository(PostDataStoreFactory postDataStoreFactory,
                               PostEntityDataMapper postEntityDataMapper) {
        this.postDataStoreFactory = postDataStoreFactory;
        this.postEntityDataMapper = postEntityDataMapper;
    }

    @Override
    public Observable<List<Post>> posts() {
        final PostDataStore postDataStore = this.postDataStoreFactory.createCloudDataStore();
        return postDataStore.postEntityList().map(this.postEntityDataMapper::transform);
    }

    @Override
    public Observable<Post> post(int postId) {
        final PostDataStore postDataStore = this.postDataStoreFactory.createCloudDataStore();
        return postDataStore.postEntityDetails(postId).map(this.postEntityDataMapper::transform);
    }
}
