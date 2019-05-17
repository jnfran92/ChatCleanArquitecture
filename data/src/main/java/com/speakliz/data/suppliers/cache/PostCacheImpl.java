package com.speakliz.data.suppliers.cache;

import android.content.Context;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.data.suppliers.cache.serializer.Serializer;
import com.speakliz.data.suppliers.utils.executor.ThreadExecutor;

import java.io.File;

import io.reactivex.Observable;

/**
 * Implementation of interface {@link PostCache} to get data from cache, shared preferences, etc.
 * It is a Singleton
 *
 */

public class PostCacheImpl implements PostCache {


    private static final String SETTINGS_FILE_NAME = "com.speakliz.data.SETTINGS";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "post_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final FileManager fileManager;
    private final File cacheDir;
    private final ThreadExecutor threadExecutor;
    private final Serializer serializer;


    private PostCacheImpl(Context context, FileManager fileManager, File cacheDir, ThreadExecutor threadExecutor, Serializer serializer) {

        if (context == null || serializer == null || fileManager == null || threadExecutor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }

        this.context = context;
        this.fileManager = fileManager;
        this.cacheDir = cacheDir;
        this.threadExecutor = threadExecutor;
        this.serializer = serializer;
    }

    @Override
    public Observable<PostEntity> get(int postId) {
        return null;
    }

    @Override
    public void put(PostEntity postEntity) {

    }

    @Override
    public boolean isCached(int postId) {
        return false;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {

    }
}
