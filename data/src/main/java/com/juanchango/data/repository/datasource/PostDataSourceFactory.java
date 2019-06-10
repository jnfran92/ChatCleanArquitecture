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

    @Inject
    PostDataSourceFactory(Context context, PostCache postCache) {
        this.context = context;
        this.postCache = postCache;
    }

    public PostDataSource createDiskDataSource(int postId){
        PostDataSource postDataSource;

        if(!this.postCache.isExpired() && this.postCache.isCached(postId)){
               postDataSource = new DiskPostDataSource(postCache);
        } else{
            postDataSource = createCloudDataSource();
        }
        return postDataSource;
    }

    public PostDataSource createCloudDataSource(){
        final PostEntityFromJsonMapper postEntityFromJsonMapper = new PostEntityFromJsonMapper();
        final RestApi restApi = new RestApiImpl(context, postEntityFromJsonMapper);

        return new CloudPostDataSource(restApi, postCache);
    }
}
