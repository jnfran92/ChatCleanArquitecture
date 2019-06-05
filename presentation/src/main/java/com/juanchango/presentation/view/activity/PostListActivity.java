package com.juanchango.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.juanchango.presentation.R;
import com.juanchango.presentation.di.component.DaggerPostComponent;
import com.juanchango.presentation.di.component.PostComponent;
import com.juanchango.presentation.navigation.Navigator;
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
public class PostListActivity extends BaseActivity  implements PostListView {

    @BindView(R.id.rv_postActivity_list)
    RecyclerView recyclerViewPosts;

    @BindView(R.id.pb_postActivity_loading)
    ProgressBar progressBarLoading;

    @Inject PostsAdapter postsAdapter;
    @Inject PostListPresenter postListPresenter;
    @Inject PostAdapterLayoutManager postAdapterLayoutManager;

    /**
     * Creates and intent used for {@link Navigator}
     *
     * @param context Application Context.
     * @return  Intent for this class.
     */
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, PostListActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        ButterKnife.bind(this);
        Timber.i("onCreate");

        this.initInjection();
        this.startViews();
    }

    private void initInjection(){
        // Activity-inj

        PostComponent postComponent = DaggerPostComponent
                .builder()
                .applicationComponent(this.getApplicationComponent())
                .activityModule(this.getActivityModule())
                .build();

        postComponent.inject(this);
    }

    private void startViews(){
        // Presenter
        postListPresenter.setView(this);
        // Adapter
        postsAdapter.setListener(new OnItemListener());
        // RecyclerView
        recyclerViewPosts.setLayoutManager(postAdapterLayoutManager);
        recyclerViewPosts.setAdapter(postsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("onResume");
        Timber.i("onResume(): Initialize presenter");
        postListPresenter.initialize();
        postListPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        postListPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        postListPresenter.destroy();
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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context context() {
        return getApplicationContext();
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

    // Listener Recycler View Class
    private final class OnItemListener implements PostsAdapter.Listener{
        @Override
        public void onClickItem(PostViewModel postViewModel) {
            Timber.i("onClickItem(): Post Id %d", postViewModel.getPostId());
            PostListActivity.this.navigator.navigateToPostDetails(PostListActivity.this);
        }
    }


}
