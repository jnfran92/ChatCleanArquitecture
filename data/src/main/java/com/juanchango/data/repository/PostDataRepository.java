package com.juanchango.data.repository;

import com.juanchango.data.entity.mapper.PostFromEntityMapper;
import com.juanchango.data.repository.datasource.PostDataSource;
import com.juanchango.data.repository.datasource.PostDataSourceFactory;
import com.juanchango.domain.model.PostModel;
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

    private final PostDataSourceFactory postDataStoreFactory;
    private final PostFromEntityMapper postEntityDataMapper;

    @Inject
    PostDataRepository(PostDataSourceFactory postDataStoreFactory,
                       PostFromEntityMapper postEntityDataMapper) {
        this.postDataStoreFactory = postDataStoreFactory;
        this.postEntityDataMapper = postEntityDataMapper;
    }

    private static PostDataRepository instance;
    public static PostDataRepository getInstance(PostDataSourceFactory postDataStoreFactory,
                                                 PostFromEntityMapper postEntityDataMapper){
        if(instance == null){
            instance = new PostDataRepository(postDataStoreFactory, postEntityDataMapper);
        }
        return instance;
    }

    @Override
    public Observable<List<PostModel>> posts() {
        final PostDataSource postDataStore = this.postDataStoreFactory.createCloudDataStore();
        return postDataStore.postEntityList().map(this.postEntityDataMapper::transform);
    }

    @Override
    public Observable<PostModel> post(int postId) {
        final PostDataSource postDataStore = this.postDataStoreFactory.createCloudDataStore();
        return postDataStore.postEntityDetails(postId).map(this.postEntityDataMapper::transform);
    }
}
