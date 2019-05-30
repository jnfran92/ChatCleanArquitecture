package com.juanchango.data.repository.datasource;

import android.content.Context;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.entity.mapper.PostEntityFromJsonMapper;
import com.juanchango.data.suppliers.cache.PostCache;
import com.juanchango.data.suppliers.net.RestApi;
import com.juanchango.data.suppliers.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory creates a {@link PostDataSource} implementation according needs
 * to get {@link PostEntity}
 * Attention: SINGLETON
 */
@Singleton
public class PostDataSourceFactory {

    private final Context context;
    private final PostCache postCache;

    // It needs Cache
    @Inject
    PostDataSourceFactory(Context context, PostCache postCache) {
        this.context = context;
        this.postCache = postCache;
    }

    private static PostDataSourceFactory instance;
    public static PostDataSourceFactory getInstance(Context context, PostCache postCache){
        if(instance == null){
            instance = new PostDataSourceFactory(context, postCache);
        }
        return instance;
    }

    public PostDataSource createDiskDataStore(int postId){
        PostDataSource postDataStore;

        if(!this.postCache.isExpired() && this.postCache.isCached(postId)){
               postDataStore = new DiskPostDataSource(postCache);
        } else{
            postDataStore = createCloudDataStore();
        }
        return postDataStore;
    }

    public PostDataSource createCloudDataStore(){
        final PostEntityFromJsonMapper postEntityJsonMapper = new PostEntityFromJsonMapper();
        final RestApi restApi = new RestApiImpl(context, postEntityJsonMapper);

        return new CloudPostDataSource(restApi, postCache);
    }
}
