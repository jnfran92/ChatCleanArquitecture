package com.juanchango.presentation.view.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.juanchango.presentation.PlayingCleanApplication;
import com.juanchango.presentation.di.component.ApplicationComponent;
import com.juanchango.presentation.di.module.ActivityModule;
import com.juanchango.presentation.navigation.Navigator;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Base {@link Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends Activity {

    @Inject
    protected Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        Timber.i("onCreate(): ");
    }


    protected ApplicationComponent getApplicationComponent(){
        return ((PlayingCleanApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

}
