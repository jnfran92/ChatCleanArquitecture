package com.juanchango.presentation;

import android.app.Application;
import android.content.res.Configuration;

import com.juanchango.presentation.di.component.ApplicationComponent;
import com.juanchango.presentation.di.component.DaggerApplicationComponent;
import com.juanchango.presentation.di.module.ApplicationModule;

import timber.log.Timber;

/**
 * Custom application subclass
 */
public class PlayingCleanApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        Timber.i("onCreate(): PlayingCleanApplication");
        initInject();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Timber.i("onConfigurationChanged(): ");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Timber.i("onLowMemory(): ");
    }

    void initInject(){
        Timber.i("initInject(): ");
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
