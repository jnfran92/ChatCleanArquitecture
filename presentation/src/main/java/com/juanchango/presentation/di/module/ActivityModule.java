package com.juanchango.presentation.di.module;

import com.juanchango.presentation.di.PerActivity;
import com.juanchango.presentation.di.component.ApplicationComponent;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    @Provides
    CompositeDisposable compositeDisposable(){
        return new CompositeDisposable();
    }
}
