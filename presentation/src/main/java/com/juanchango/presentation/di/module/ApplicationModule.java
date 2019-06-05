package com.juanchango.presentation.di.module;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.juanchango.data.executor.JobExecutor;
import com.juanchango.data.repository.PostDataRepository;
import com.juanchango.data.suppliers.cache.PostCache;
import com.juanchango.data.suppliers.cache.PostCacheImpl;
import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;
import com.juanchango.domain.repository.PostRepository;
import com.juanchango.presentation.UIThread;
import com.juanchango.presentation.di.ApplicationContext;
import com.juanchango.presentation.presenter.PostListPresenter;
import com.juanchango.presentation.presenter.Presenter;
import com.juanchango.presentation.view.adapter.PostAdapterLayoutManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    Context context(){
        return context;
    }

    @Provides @Singleton
    PostCache postCache(PostCacheImpl postCache){
        return postCache;
    }

    @Provides @Singleton
    ThreadExecutor threadExecutor(JobExecutor jobExecutor){
        return jobExecutor;
    }

    @Provides @Singleton
    PostRepository postRepository(PostDataRepository postDataRepository){
        return postDataRepository;
    }

    @Provides @Singleton
    PostExecutionThread postExecutionThread(UIThread uiThread){
        return uiThread;
    }

    @Provides
    CompositeDisposable compositeDisposable(){
        return new CompositeDisposable();
    }
}
