package com.speakliz.data.repository;

import com.speakliz.data.entity.mapper.PostEntityDataMapper;
import com.speakliz.data.repository.datasource.PostDataStore;
import com.speakliz.data.repository.datasource.PostDataStoreFactory;
import com.speakliz.domain.model.Post;
import com.speakliz.domain.repository.PostRepository;

import java.util.List;
import io.reactivex.Observable;

/**
 * The interface between data Layer and domain Layer
 */
public class PostDataRepository implements PostRepository {

    private final PostDataStoreFactory postDataStoreFactory;
    private final PostEntityDataMapper postEntityDataMapper;

    public PostDataRepository(PostDataStoreFactory postDataStoreFactory, PostEntityDataMapper postEntityDataMapper) {
        this.postDataStoreFactory = postDataStoreFactory;
        this.postEntityDataMapper = postEntityDataMapper;
    }

    @Override
    public Observable<List<Post>> posts() {
        final PostDataStore postDataStore = this.postDataStoreFactory.createDiskDataStore();
        return postDataStore.postEntityList().map(this.postEntityDataMapper::transform);
    }

    @Override
    public Observable<Post> post(int postId) {
        final PostDataStore postDataStore = this.postDataStoreFactory.createDiskDataStore();
        return postDataStore.postEntityDetails(postId).map(this.postEntityDataMapper::transform);
    }
}
