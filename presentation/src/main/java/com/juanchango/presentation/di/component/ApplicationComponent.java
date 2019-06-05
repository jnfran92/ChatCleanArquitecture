package com.juanchango.presentation.di.component;

import android.content.Context;

import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;
import com.juanchango.domain.repository.PostRepository;
import com.juanchango.presentation.di.module.ApplicationModule;
import com.juanchango.presentation.navigation.Navigator;
import com.juanchango.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;
import dagger.Component;

/**
 * A component whose lifetime is the life od the application.
 */
@Singleton // Constrains this component to one-per-application or un-scoped bindings.
@Component(modules = {ApplicationModule.class})
public interface  ApplicationComponent {
    void inject(BaseActivity baseActivity); // Injects navigator which is Singleton

    // Exposed to sub-graphs
    Context Context();
    PostRepository postRepository();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    Navigator navigator();
}
