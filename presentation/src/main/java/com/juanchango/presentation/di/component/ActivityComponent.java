package com.juanchango.presentation.di.component;

import com.juanchango.presentation.di.PerActivity;
import com.juanchango.presentation.di.module.ActivityModule;
import com.juanchango.presentation.di.module.ApplicationModule;
import com.juanchango.presentation.presenter.PostListPresenter;
import com.juanchango.presentation.view.activity.PostListActivity;

import javax.inject.Singleton;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(PostListActivity postListActivity);
}
