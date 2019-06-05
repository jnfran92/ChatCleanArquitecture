package com.juanchango.presentation.di.component;

import com.juanchango.data.repository.PostDataRepository;
import com.juanchango.presentation.di.module.ApplicationModule;
import com.juanchango.presentation.presenter.PostListPresenter;
import com.juanchango.presentation.view.activity.PostListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
//    PostDataRepository postDataRepository();
//    PostListPresenter postListPresenter();
    void inject(PostListActivity postListActivity);

}
