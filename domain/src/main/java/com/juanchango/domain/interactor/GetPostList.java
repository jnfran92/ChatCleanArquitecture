package com.juanchango.domain.interactor;

import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;
import com.juanchango.domain.model.PostModel;
import com.juanchango.domain.repository.PostRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class GetPostList extends UseCase<List<PostModel>, Void>{

    private final PostRepository postRepository;

    @Inject
    public GetPostList(PostRepository postRepository, ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread,
                       CompositeDisposable compositeDisposable) {
        super(threadExecutor, postExecutionThread, compositeDisposable);
        this.postRepository = postRepository;
    }

    @Override
    Observable<List<PostModel>> buildUseCaseObservable(Void aVoid) {
        return this.postRepository.posts();
    }
}
