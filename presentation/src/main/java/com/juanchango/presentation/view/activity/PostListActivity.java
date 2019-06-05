package com.juanchango.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.juanchango.presentation.PlayingCleanApplication;
import com.juanchango.presentation.R;
import com.juanchango.presentation.di.component.ActivityComponent;
import com.juanchango.presentation.di.component.ApplicationComponent;
import com.juanchango.presentation.di.component.DaggerActivityComponent;
import com.juanchango.presentation.presenter.PostListPresenter;
import com.juanchango.presentation.view.PostListView;
import com.juanchango.presentation.view.adapter.PostAdapterLayoutManager;
import com.juanchango.presentation.view.adapter.PostsAdapter;
import com.juanchango.presentation.viewmodel.PostViewModel;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


/**
 * Activity to render a Collection of {@link PostViewModel}, it implements {@link PostListView}
 * interface.
 */
public class PostListActivity extends AppCompatActivity  implements PostListView {

    @BindView(R.id.rv_postActivity_list)
    RecyclerView recyclerViewPosts;

    @BindView(R.id.pb_postActivity_loading)
    ProgressBar progressBarLoading;

    @Inject Context context;
    @Inject PostsAdapter postsAdapter;
    @Inject PostListPresenter postListPresenter;
    @Inject PostAdapterLayoutManager postAdapterLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        ButterKnife.bind(this);
        Timber.i("onCreate");

        initInjection();
        startViews();
    }

    private void initInjection(){

        // App-inj
        PlayingCleanApplication application = (PlayingCleanApplication) getApplication();
        ApplicationComponent applicationComponent = application.getApplicationComponent();

        // Activity-inj
        ActivityComponent activityComponent = DaggerActivityComponent
                .builder()
                .applicationComponent(applicationComponent)
                .build();
        activityComponent.inject(this);
    }

    private void startViews(){
        // Presenter
        postListPresenter.setView(this);
        // RecyclerView
        recyclerViewPosts.setLayoutManager(postAdapterLayoutManager);
        // Adapter
        recyclerViewPosts.setAdapter(postsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("onResume");
        Timber.i("onResume(): Initialize presenter");
        postListPresenter.initialize();
    }

    @Override
    public void renderPostList(Collection<PostViewModel> postViewModels) {
        Timber.d("renderPostList(): ");
        postsAdapter.setPostViewModelList(postViewModels);
    }

    @Override
    public void viewPost(PostViewModel postViewModel) {
        Timber.d("viewPost(): ");
    }

    @Override
    public void showLoading() {
        Timber.d("showLoading(): ");
        showProgressBar();
    }

    @Override
    public void hideLoading() {
        Timber.d("hideLoading(): ");
        hideProgressBar();
    }

    @Override
    public void showRetry() {
        Timber.d("showRetry(): ");
    }

    @Override
    public void hideRetry() {
        Timber.d("hideRetry(): ");
    }

    @Override
    public void showError(String message) {
        Timber.d("showError(): ");
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context context() {
        return context;
    }

    /*
    Progress Bar methods
     */
    private void showProgressBar(){
        progressBarLoading.setVisibility(View.VISIBLE);
        progressBarLoading.setIndeterminate(true);
    }
    private void hideProgressBar(){
        progressBarLoading.setVisibility(View.GONE);
    }

}
