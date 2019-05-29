package com.juanchango.domain.interactor;

import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;
import com.juanchango.domain.model.Post;
import com.juanchango.domain.repository.PostRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class GetPostList extends UseCase<List<Post>, Void>{

    private final PostRepository postRepository;

    @Inject
    public GetPostList(PostRepository postRepository, ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread,
                       CompositeDisposable compositeDisposable) {
        super(threadExecutor, postExecutionThread, compositeDisposable);
        this.postRepository = postRepository;
    }

    @Override
    Observable<List<Post>> buildUseCaseObservable(Void aVoid) {
        return this.postRepository.posts();
    }
}
