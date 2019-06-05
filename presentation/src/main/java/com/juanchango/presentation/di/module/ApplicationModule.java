package com.juanchango.presentation.di.module;

import android.content.Context;

import com.juanchango.data.executor.JobExecutor;
import com.juanchango.data.repository.PostDataRepository;
import com.juanchango.data.suppliers.cache.PostCache;
import com.juanchango.data.suppliers.cache.PostCacheImpl;
import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;
import com.juanchango.domain.repository.PostRepository;
import com.juanchango.presentation.PlayingCleanApplication;
import com.juanchango.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final PlayingCleanApplication application;

    public ApplicationModule(PlayingCleanApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context context(){
        return this.application;
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

}
