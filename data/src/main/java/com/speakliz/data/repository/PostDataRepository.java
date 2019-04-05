package com.speakliz.data.repository;

import com.speakliz.data.repository.datasource.PostDataStoreFactory;
import com.speakliz.domain.model.Post;
import com.speakliz.domain.repository.PostRepository;

import java.util.List;

import io.reactivex.Observable;

public class PostDataRepository implements PostRepository {

    private final PostDataStoreFactory postDataStoreFactory;

    public PostDataRepository(PostDataStoreFactory postDataStoreFactory) {
        this.postDataStoreFactory = postDataStoreFactory;
    }

    @Override
    public Observable<List<Post>> posts() {
        return null;
    }

    @Override
    public Observable<Post> post(int postId) {
        return null;
    }
}
