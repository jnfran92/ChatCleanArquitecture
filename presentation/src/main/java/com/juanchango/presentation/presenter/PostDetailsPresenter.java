package com.juanchango.presentation.presenter;

import androidx.annotation.NonNull;

import com.juanchango.domain.exception.ErrorBundle;
import com.juanchango.domain.interactor.DefaultObserver;
import com.juanchango.domain.interactor.GetPostDetails;
import com.juanchango.domain.model.PostModel;
import com.juanchango.presentation.exception.ErrorMessageFactory;
import com.juanchango.presentation.mapper.PostViewModelFromModelMapper;
import com.juanchango.presentation.view.PostDetailsView;
import com.juanchango.presentation.viewmodel.PostViewModel;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between Domain Layer and View Layer. View must shown
 * the {@link PostViewModel} object.
 */
public class PostDetailsPresenter implements Presenter{

    private PostDetailsView view;

    private final GetPostDetails getPostDetailsUseCase;
    private final PostViewModelFromModelMapper postViewModelFromModelMapper;

    @Inject
    public PostDetailsPresenter(GetPostDetails getPostDetails, PostViewModelFromModelMapper postViewModelFromModelMapper) {
        this.getPostDetailsUseCase = getPostDetails;
        this.postViewModelFromModelMapper = postViewModelFromModelMapper;
    }

    /**
     * Set View {@link PostDetailsView}
     */
    public void setView(@NonNull PostDetailsView view){
        this.view = view;
    }

    /**
     * Initialize the presenter
     */
    public void initialize(int postId){
        this.hideViewRetry();
        this.showViewLoading();
        this.getPostDetails(postId);
    }

    private void getPostDetails(int postId) {
        this.getPostDetailsUseCase.execute(new PostDetailsObserver(), GetPostDetails.Params.forPost(postId));
    }

    /*
    Presenter implementations
    */
    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        getPostDetailsUseCase.dispose();
        view = null;
    }

    /*
    Re-write in class methods the LoadDataView implementation
     */
    private void showViewLoading(){
        view.showLoading();
    }

    private void hideViewLoading(){
        view.hideLoading();
    }

    private void showViewRetry(){
        view.showRetry();
    }

    private void hideViewRetry(){
        view.hideRetry();
    }

    private void showViewError(ErrorBundle errorBundle){
        final String errorMessage
                = ErrorMessageFactory.create(this.view.context(), errorBundle.getException());
        view.showError(errorMessage);
    }

    /*
    Render
     */
    private void showPostDetailsInView(PostModel postModel){
        final PostViewModel postViewModel = this.postViewModelFromModelMapper.transform(postModel);
        this.view.renderPost(postViewModel);
    }

    /*
    Listener for observable
     */
    private final class PostDetailsObserver extends DefaultObserver<PostModel>{
        @Override
        public void onNext(PostModel postModel) {
            PostDetailsPresenter.this.hideViewLoading();
            PostDetailsPresenter.this.showPostDetailsInView(postModel);
        }

        @Override
        public void onError(Throwable e) {
            PostDetailsPresenter.this.hideViewLoading();
            PostDetailsPresenter.this.showViewError((ErrorBundle) e);
            PostDetailsPresenter.this.showViewRetry();
        }

        @Override
        public void onComplete() {
            PostDetailsPresenter.this.hideViewLoading();
        }
    }
}
