package com.juanchango.presentation.di.component;

import android.app.Activity;

import com.juanchango.presentation.di.PerActivity;
import com.juanchango.presentation.di.module.ActivityModule;
import com.juanchango.presentation.view.activity.PostListActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    // exposed to sub-graphs
    Activity activity();
}
