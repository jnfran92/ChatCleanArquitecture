package com.juanchango.presentation.di.module;

import android.content.Context;

import com.juanchango.data.executor.JobExecutor;
import com.juanchango.data.repository.PostDataRepository;
import com.juanchango.data.suppliers.cache.PostCache;
import com.juanchango.data.suppliers.cache.PostCacheImpl;
import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;
import com.juanchango.domain.repository.PostRepository;
import com.juanchango.presentation.UIThread;
import com.juanchango.presentation.presenter.PostListPresenter;
import com.juanchango.presentation.presenter.Presenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    Context context(){
        return context.getApplicationContext();
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

    /*
    Activity providers
     */
    @Provides
    CompositeDisposable compositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    Presenter presenter(PostListPresenter postListPresenter){
        return postListPresenter;
    }

}
