package com.juanchango.presentation.presenter;

import com.juanchango.domain.exception.DefaultErrorBundle;
import com.juanchango.domain.exception.ErrorBundle;
import com.juanchango.domain.interactor.DefaultObserver;
import com.juanchango.domain.interactor.GetPostList;
import com.juanchango.domain.model.PostModel;
import com.juanchango.presentation.exception.ErrorMessageFactory;
import com.juanchango.presentation.mapper.PostViewModelFromModelMapper;
import com.juanchango.presentation.view.PostListView;
import com.juanchango.presentation.viewmodel.PostViewModel;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation layer.
 */
public class PostListPresenter implements Presenter {

    private PostListView postListView;

    private final GetPostList getPostListUseCase;
    private final PostViewModelFromModelMapper postViewModelFromModelMapper;

    @Inject
    public PostListPresenter(GetPostList getPostList, PostViewModelFromModelMapper postViewModelFromModelMapper) {
        this.getPostListUseCase = getPostList;
        this.postViewModelFromModelMapper = postViewModelFromModelMapper;
    }

    public void initialize(){
        this.loadPostList();
    }

    private void loadPostList(){
        this.hideViewRetry();
        this.showViewLoading();
        this.getPostList();
    }

    private void getPostList(){
        this.getPostListUseCase.execute(new UserListObserver(), null);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getPostListUseCase.dispose();
        this.postListView = null;
    }

    private void showViewLoading(){
        postListView.showLoading();
    }

    private void hideViewLoading(){
        postListView.hideLoading();
    }

    private void showViewRetry(){
        postListView.showRetry();
    }

    private void hideViewRetry(){
        postListView.hideRetry();
    }

    private void showCollectionInView(Collection<PostModel> postModels){
        final Collection<PostViewModel> postViewModels =
                this.postViewModelFromModelMapper.transform(postModels);
        this.postListView.renderPostList(postViewModels);
    }

    private void showViewErrorMessage(ErrorBundle errorBundle){
        String errorMessage = ErrorMessageFactory.create(this.postListView.context(),
                errorBundle.getException());
        this.postListView.showError(errorMessage);
    }

    private final class UserListObserver extends DefaultObserver<List<PostModel>> {
        @Override
        public void onNext(List<PostModel> posts) {
            PostListPresenter.this.showCollectionInView(posts);
        }

        @Override
        public void onError(Throwable e) {
            PostListPresenter.this.hideViewLoading();
            PostListPresenter.this.showViewErrorMessage(new DefaultErrorBundle((Exception) e));
            PostListPresenter.this.showViewRetry();
        }

        @Override
        public void onComplete() {
            PostListPresenter.this.hideViewLoading();
        }
    }
}
