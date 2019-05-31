package com.juanchango.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
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
import com.juanchango.domain.model.PostModel;
import com.juanchango.domain.repository.PostRepository;
import com.juanchango.presentation.R;
import com.juanchango.presentation.UiThread;
import com.juanchango.presentation.mapper.PostViewModelFromModelMapper;
import com.juanchango.presentation.view.adapter.PostsAdapter;
import com.juanchango.presentation.viewmodel.PostViewModel;

import java.io.File;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

public class PostListActivity extends AppCompatActivity {

    @BindView(R.id.rv_postActivity_list)
    RecyclerView recyclerViewPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        ButterKnife.bind(this);

//        Timber.plant(new Timber.DebugTree());
        Timber.i("onCreate");

        // Data
        Context context = getApplicationContext();
        FileManager fileManager = new FileManager();
        File cacheDir = getCacheDir();

        ThreadExecutor threadExecutorData = JobExecutor.getInstance();        // Singleton
        Serializer serializer = Serializer.getInstance(); // Singleton

        PostCache postCache = PostCacheImpl.getInstance(context, fileManager, cacheDir, threadExecutorData, serializer);         // Singleton


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

        // View
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerViewPosts.setLayoutManager(linearLayoutManager);
        recyclerViewPosts.setItemAnimator(new DefaultItemAnimator());

        postsAdapter = new PostsAdapter(context);
//        postsAdapter.setPostViewModelList(postViewModels);

        recyclerViewPosts.setAdapter(postsAdapter);

    }

    GetPostList getPostList;
    PostViewModelFromModelMapper postViewModelFromModelMapper;

    Collection<PostViewModel> postViewModels;
    PostsAdapter postsAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("onResume");
        getData();
    }


    public void getData(){
        Timber.i("getData");

        DisposableObserver<List<PostModel>> listDisposableObserver = new DisposableObserver<List<PostModel>>() {
            @Override
            public void onNext(List<PostModel> posts) {
                Timber.i("onNext");

                for (PostModel post:posts){
                    Timber.i("PostModel # %d = %s", post.getPostId(), post.getTitle());
                }

                postViewModels = postViewModelFromModelMapper.transform(posts);
            }

            @Override
            public void onError(Throwable e) {
                Timber.i("onError %s", e.toString());
            }

            @Override
            public void onComplete() {
                Timber.i("onComplete");
                postsAdapter.setPostViewModelList(postViewModels);
            }
        };

        getPostList.execute(listDisposableObserver, null);
    }


}
