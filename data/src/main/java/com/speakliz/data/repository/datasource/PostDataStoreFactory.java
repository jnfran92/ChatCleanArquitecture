package com.speakliz.data.repository.datasource;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.data.utils.local.LocalApi;
import com.speakliz.data.utils.local.LocalApiImpl;

/**
 * Factory creates a {@link PostDataStore} implementation according needs
 * to get {@link PostEntity}
 * Attention: SINGLETON
 */

public class PostDataStoreFactory {

    private static PostDataStoreFactory instance;

    // Here we need the context of the app!
    private PostDataStoreFactory() {
    }

    public static PostDataStoreFactory getInstance(){
        if(instance != null) {
            instance = new PostDataStoreFactory();
        }
        return instance;
    }

    public PostDataStore createDiskDataStore(){
        final LocalApi localApi =  new LocalApiImpl();
        return new DiskPostDataStore(localApi);
    }
}
