package com.juanchango.presentation;

import android.app.Application;
import android.content.res.Configuration;

import timber.log.Timber;

/**
 * Custom application subclass
 */
public class PlayingCleanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        Timber.i("onCreate(): PlayingCleanApplication");
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

}
