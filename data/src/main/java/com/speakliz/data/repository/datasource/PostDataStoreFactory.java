package com.speakliz.data.repository.datasource;

import android.content.Context;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.data.suppliers.local.LocalApi;
import com.speakliz.data.suppliers.local.LocalApiImpl;

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
}
