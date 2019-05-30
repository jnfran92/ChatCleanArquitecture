package com.juanchango.data.repository.datasource;

import android.content.Context;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.entity.mapper.PostEntityJsonMapper;
import com.juanchango.data.suppliers.cache.PostCache;
import com.juanchango.data.suppliers.local.LocalApi;
import com.juanchango.data.suppliers.local.LocalApiImpl;
import com.juanchango.data.suppliers.net.RestApi;
import com.juanchango.data.suppliers.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory creates a {@link PostDataStore} implementation according needs
 * to get {@link PostEntity}
 * Attention: SINGLETON
 */
@Singleton
public class PostDataStoreFactory {

    private final Context context;
    private final PostCache postCache;

    // It needs Cache
    @Inject
    PostDataStoreFactory(Context context, PostCache postCache) {
        this.context = context;
        this.postCache = postCache;
    }

    private static PostDataStoreFactory instance;
    public static PostDataStoreFactory getInstance(Context context, PostCache postCache){
        if(instance == null){
            instance = new PostDataStoreFactory(context, postCache);
        }
        return instance;
    }

    public PostDataStore createDiskDataStore(int postId){
        PostDataStore postDataStore;

        if(!this.postCache.isExpired() && this.postCache.isCached(postId)){
               postDataStore = new DiskPostDataStore(postCache);
        } else{
            postDataStore = createCloudDataStore();
        }
        return postDataStore;
    }

    public PostDataStore createCloudDataStore(){
        final PostEntityJsonMapper postEntityJsonMapper = new PostEntityJsonMapper();
        final RestApi restApi = new RestApiImpl(context, postEntityJsonMapper);

        return new CloudPostDataStore(restApi, postCache);
    }
}
