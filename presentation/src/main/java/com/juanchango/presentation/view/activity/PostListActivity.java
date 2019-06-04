package com.juanchango.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.juanchango.data.entity.mapper.PostFromEntityMapper;
import com.juanchango.data.executor.JobExecutor;
import com.juanchango.data.repository.PostDataRepository;
import com.juanchango.data.repository.datasource.PostDataSourceFactory;
import com.juanchango.data.suppliers.cache.FileManager;
import com.juanchango.data.suppliers.cache.PostCache;
import com.juanchango.data.suppliers.cache.PostCacheImpl;
import com.juanchango.data.suppliers.cache.serializer.Serializer;
import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;
import com.juanchango.domain.interactor.GetPostList;
import com.juanchango.domain.repository.PostRepository;
import com.juanchango.presentation.R;
import com.juanchango.presentation.UiThread;
//import com.juanchango.presentation.di.component.DaggerDataComponent;
//import com.juanchango.presentation.di.component.DataComponent;
import com.juanchango.presentation.di.module.ContextModule;
import com.juanchango.presentation.mapper.PostViewModelFromModelMapper;
import com.juanchango.presentation.presenter.PostListPresenter;
import com.juanchango.presentation.view.PostListView;
import com.juanchango.presentation.view.adapter.PostsAdapter;
import com.juanchango.presentation.viewmodel.PostViewModel;

import java.io.File;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
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

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        ButterKnife.bind(this);

        Timber.i("onCreate");


        // Data
        context = getApplicationContext();

//        DataComponent dataComponentDagger = DaggerDataComponent.builder()
//                .contextModule(new ContextModule(this))
//                .build();
//
//        PostCache postCache = dataComponentDagger.getPostCacheImpl();
        PostCache postCache = null;

        // Domain
        PostDataSourceFactory postDataStoreFactory = PostDataSourceFactory.getInstance(context,
                postCache);
        PostFromEntityMapper postFromEntityMapper = PostFromEntityMapper.getInstance(); // Singleton
        PostRepository postRepository = PostDataRepository.getInstance(postDataStoreFactory, postFromEntityMapper); // Singleton


        // Presentation
        ThreadExecutor threadExecutorDomain = JobExecutor.getInstance(); // Singleton
        PostExecutionThread uiThread = UiThread.getInstance();  // Singleton

        CompositeDisposable compositeDisposable = new CompositeDisposable();

        getPostList = new GetPostList(postRepository, threadExecutorDomain, uiThread, compositeDisposable);
        postViewModelFromModelMapper = PostViewModelFromModelMapper.getInstance(); // Singleton

        // Presenters
        postListPresenter = new PostListPresenter(getPostList,
                postViewModelFromModelMapper);
        postListPresenter.setView(this);


        // RecyclerView
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerViewPosts.setLayoutManager(linearLayoutManager);

        // Adapter
        postsAdapter = new PostsAdapter(context);
        recyclerViewPosts.setAdapter(postsAdapter);

    }

    GetPostList getPostList;
    PostViewModelFromModelMapper postViewModelFromModelMapper;

    Collection<PostViewModel> postViewModels;
    PostsAdapter postsAdapter;

    PostListPresenter postListPresenter;

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
