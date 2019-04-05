package com.speakliz.data.repository.datasource;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.data.utils.local.LocalApi;
import com.speakliz.data.utils.local.LocalApiImpl;

/**
 * Factory creates a {@link PostDataStore} implementation according needs
 * to get {@link PostEntity} SINGLETON
 */

//Singleton
public class PostDataStoreFactory {

    private PostDataStoreFactory instance;

    private PostDataStoreFactory() {
    }

    public PostDataStoreFactory getInstance(){
        if(instance != null) {
            this.instance = new PostDataStoreFactory();
        }
        return instance;
    }

    public PostDataStore createDiskDataStore(){
        final LocalApi localApi =  new LocalApiImpl();
        return new DiskPostDataStore(localApi);
    }
}
