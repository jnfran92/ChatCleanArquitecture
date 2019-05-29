package com.juanchango.domain.interactor;

import com.fernandocejas.arrow.checks.Preconditions;
import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;
import com.juanchango.domain.model.Post;
import com.juanchango.domain.repository.PostRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class GetPostDetails extends UseCase<Post, GetPostDetails.Params>{

    private final PostRepository postRepository;

    @Inject
    public GetPostDetails(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CompositeDisposable compositeDisposable, PostRepository postRepository) {
        super(threadExecutor, postExecutionThread, compositeDisposable);
        this.postRepository = postRepository;
    }

    @Override
    Observable<Post> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params);
        return this.postRepository.post(params.postId);
    }


    public static final class Params {
        private final int postId;

        private Params(int postId) {
            this.postId = postId;
        }

        public static Params forPost(int postId){
            return new Params(postId);
        }
    }


}
