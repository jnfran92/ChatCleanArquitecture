package com.juanchango.domain.repository;

import com.juanchango.domain.model.Post;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface between Data Layer and Domain Layer
 */
public interface PostRepository {
    Observable<List<Post>> posts();
    Observable<Post> post(int postId);
}
