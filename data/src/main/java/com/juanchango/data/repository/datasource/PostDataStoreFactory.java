package com.juanchango.data.repository.datasource;

import android.content.Context;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.entity.mapper.PostEntityJsonMapper;
import com.juanchango.data.suppliers.local.LocalApi;
import com.juanchango.data.suppliers.local.LocalApiImpl;
import com.juanchango.data.suppliers.net.RestApi;
import com.juanchango.data.suppliers.net.RestApiImpl;

/**
 * Factory creates a {@link PostDataStore} implementation according needs
 * to get {@link PostEntity}
 * Attention: SINGLETON
 */

public class PostDataStoreFactory {

    private static PostDataStoreFactory instance;
    private final Context context;

    // It needs Cache
    public PostDataStoreFactory(Context context) {
        this.context = context;
    }

    public static PostDataStoreFactory getInstance(Context context){
        if(instance != null) {
            instance = new PostDataStoreFactory(context);
        }
        return instance;
    }

    public PostDataStore createDiskDataStore(){
        final LocalApi localApi =  new LocalApiImpl();
        return new DiskPostDataStore(localApi);
    }

    public PostDataStore createCloudDataStore(){
        final PostEntityJsonMapper postEntityJsonMapper = new PostEntityJsonMapper();
        final RestApi restApi = new RestApiImpl(context, postEntityJsonMapper);

        return new CloudPostDataStore(restApi);
    }
}
