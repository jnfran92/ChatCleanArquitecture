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

    private final PostDataSourceFactory postDataSourceFactory;
    private final PostFromEntityMapper postFromEntityMapper;

    @Inject
    PostDataRepository(PostDataSourceFactory postDataSourceFactory,
                       PostFromEntityMapper postFromEntityMapper) {
        this.postDataSourceFactory = postDataSourceFactory;
        this.postFromEntityMapper = postFromEntityMapper;
    }


    @Override
    public Observable<List<PostModel>> posts() {
        final PostDataSource postDataStore = this.postDataSourceFactory.createCloudDataStore();
        return postDataStore.postEntityList().map(this.postFromEntityMapper::transform);
    }

    @Override
    public Observable<PostModel> post(int postId) {
        final PostDataSource postDataStore = this.postDataSourceFactory.createCloudDataStore();
        return postDataStore.postEntityDetails(postId).map(this.postFromEntityMapper::transform);
    }
}
