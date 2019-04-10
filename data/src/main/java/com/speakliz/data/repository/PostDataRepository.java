package com.speakliz.data.repository;

import com.speakliz.data.entity.mapper.PostEntityDataMapper;
import com.speakliz.data.repository.datasource.PostDataStore;
import com.speakliz.data.repository.datasource.PostDataStoreFactory;
import com.speakliz.domain.model.Post;
import com.speakliz.domain.repository.PostRepository;

import java.util.List;
import java.util.logging.Handler;

import io.reactivex.Observable;

/**
 * The interface between data Layer and domain Layer
 * Singleton
 */
public class PostDataRepository implements PostRepository {

    private final PostDataStoreFactory postDataStoreFactory;
    private final PostEntityDataMapper postEntityDataMapper;

    private static PostDataRepository instance;

    private PostDataRepository(PostDataStoreFactory postDataStoreFactory, PostEntityDataMapper postEntityDataMapper) {
        this.postDataStoreFactory = postDataStoreFactory;
        this.postEntityDataMapper = postEntityDataMapper;
    }

    public PostDataRepository getInstance(PostDataStoreFactory postDataStoreFactory,
                                          PostEntityDataMapper postEntityDataMapper){
        if(instance == null){
            instance = new PostDataRepository(postDataStoreFactory,
                    postEntityDataMapper);
        }
        return instance;
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
