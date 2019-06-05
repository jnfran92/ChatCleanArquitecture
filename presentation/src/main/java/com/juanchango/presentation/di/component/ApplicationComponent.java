package com.juanchango.presentation.di.component;

import android.content.Context;

import com.juanchango.data.repository.PostDataRepository;
import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;
import com.juanchango.domain.repository.PostRepository;
import com.juanchango.presentation.di.module.ApplicationModule;
import com.juanchango.presentation.presenter.PostListPresenter;
import com.juanchango.presentation.presenter.Presenter;
import com.juanchango.presentation.view.activity.PostListActivity;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
//    PostDataRepository postDataRepository();
//    PostListPresenter postListPresenter();
//    void inject(PostListActivity postListActivity);

    Context Context();
    PostRepository postRepository();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    CompositeDisposable compositeDisposable();

}
