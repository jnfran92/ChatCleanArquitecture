package com.juanchango.domain.repository;

import com.juanchango.domain.model.Post;

import java.util.List;

import io.reactivex.Observable;

public interface PostRepository {

    Observable<List<Post>> posts();
    Observable<Post> post(int postId);
}
