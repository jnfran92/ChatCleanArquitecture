package com.juanchango.presentation.presenter;

import androidx.annotation.NonNull;

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

    /*
    View
     */
    private PostListView postListView;

    /*
    Use Case and Mapper
     */
    private final GetPostList getPostListUseCase;
    private final PostViewModelFromModelMapper postViewModelFromModelMapper;

    @Inject
    public PostListPresenter(GetPostList getPostList, PostViewModelFromModelMapper postViewModelFromModelMapper) {
        this.getPostListUseCase = getPostList;
        this.postViewModelFromModelMapper = postViewModelFromModelMapper;
    }

    public void setView(@NonNull PostListView view){
        this.postListView = view;
    }

    /**
     * Initializes the presenter by start retriving the user list
     */
    public void initialize(){
        this.loadPostList();
    }

    /**
     * Load all posts
     */
    private void loadPostList(){
        this.hideViewRetry();
        this.showViewLoading();
        this.getPostList();
    }

    private void getPostList(){
        this.getPostListUseCase.execute(new UserListObserver(), null);
    }

    /*
    Overrides: LoadDataView interface methods
     */
    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {
        this.getPostListUseCase.dispose();
        this.postListView = null;
    }

    /*
    Re-Write explicitly in new methods the view ones.
     */
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

    private void showViewErrorMessage(ErrorBundle errorBundle){
        String errorMessage = ErrorMessageFactory.create(this.postListView.context(),
                errorBundle.getException());
        this.postListView.showError(errorMessage);
    }

    /**
     * Show a Collection the {@link PostModel} objects set bellow in the view. This can be converted in
     * {@link PostViewModel} objects.
     */
    private void showCollectionInView(Collection<PostModel> postModels){
        final Collection<PostViewModel> postViewModels =
                this.postViewModelFromModelMapper.transform(postModels);
        this.postListView.renderPostList(postViewModels);
    }

    /**
     * Show clicked object: {@link PostViewModel}.
     */
    public void onPostClicked(PostViewModel postViewModel){
        this.postListView.viewPost(postViewModel);
    }

    /**
     * Custom class Listening data from Observable
     */
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
