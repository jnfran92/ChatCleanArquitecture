package com.juanchango.presentation.di.component;

import com.juanchango.data.repository.PostDataRepository;
import com.juanchango.data.suppliers.cache.PostCacheImpl;
import com.juanchango.presentation.di.module.ContextModule;
import com.juanchango.presentation.di.module.PostCacheModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PostCacheModule.class, ContextModule.class})
public interface DataComponent {
//    PostCacheImpl getPostCacheImpl();
//    PostDataRepository postDataRepository();
}
