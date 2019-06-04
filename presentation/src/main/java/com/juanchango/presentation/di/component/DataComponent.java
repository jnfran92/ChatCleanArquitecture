package com.juanchango.presentation.di.component;

import com.juanchango.data.suppliers.cache.PostCache;
import com.juanchango.data.suppliers.cache.PostCacheImpl;
import com.juanchango.presentation.di.module.PostCacheModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PostCacheModule.class})
public interface DataComponent {
    PostCacheImpl getPostCacheImpl();
}
