package com.juanchango.presentation.di.component;

import com.juanchango.presentation.di.PerActivity;
import com.juanchango.presentation.di.module.ActivityModule;
import com.juanchango.presentation.di.module.PostModule;
import com.juanchango.presentation.presenter.PostDetailsPresenter;
import com.juanchango.presentation.view.activity.PostDetailsActivity;
import com.juanchango.presentation.view.activity.PostListActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PostModule.class})
public interface PostComponent extends ActivityComponent{
    void inject(PostListActivity postListActivity);
    void inject(PostDetailsActivity postDetailsActivity);
}
