package com.juanchango.presentation.di.module;

import android.content.Context;

import com.juanchango.presentation.di.ActivityContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Provides @ActivityContext
    Context context(){
        return context.getApplicationContext();
    }

}
