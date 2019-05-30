package com.juanchango.domain.repository;

import com.juanchango.domain.model.PostModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface between Data Layer and Domain Layer
 */
public interface PostRepository {
    Observable<List<PostModel>> posts();
    Observable<PostModel> post(int postId);
}
