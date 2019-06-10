package com.juanchango.presentation.view.activity;

import android.app.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.juanchango.presentation.PlayingCleanApplication;
import com.juanchango.presentation.R;
import com.juanchango.presentation.di.component.ApplicationComponent;
import com.juanchango.presentation.di.module.ActivityModule;
import com.juanchango.presentation.navigation.Navigator;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Base {@link Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        Timber.i("onCreate(): ");
    }

    protected void addFragment(int containerId, Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(containerId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent(){
        return ((PlayingCleanApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }
}
