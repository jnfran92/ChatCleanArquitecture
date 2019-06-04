package com.juanchango.presentation.di.module;

import android.content.Context;

import com.juanchango.data.executor.JobExecutor;
import com.juanchango.data.suppliers.cache.FileManager;
import com.juanchango.data.suppliers.cache.serializer.Serializer;
import com.juanchango.domain.executor.ThreadExecutor;

import java.io.File;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class PostCacheModule {

    @Provides
    public FileManager fileManager(){
        return new FileManager();
    }

    @Provides
    public File cacheDir(Context context){
        return context.getCacheDir();
    }

    @Provides
    public ThreadExecutor threadExecutor(){
        return new JobExecutor();
    }

    @Provides
    public Serializer serializer(){
        return new Serializer();
    }


}
