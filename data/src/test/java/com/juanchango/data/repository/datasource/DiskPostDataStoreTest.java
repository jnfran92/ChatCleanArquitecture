package com.juanchango.data.repository.datasource;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.suppliers.local.LocalApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DiskPostDataStoreTest {

    private static final int FAKE_POST_ID = 12;

    @Mock private DiskPostDataSource mockDiskPostDataStore;
    @Mock private LocalApi mockLocalApi;

    @Before
    public void setUp(){
        mockDiskPostDataStore = new DiskPostDataSource(mockLocalApi);
    }

    @Test
    public void testPostEntityList() {
        mockDiskPostDataStore.postEntityList();
        verify(mockLocalApi).postEntityList();
    }

    @Test
    public void testPostEntityDetails() {

        PostEntity postEntity = new PostEntity();
        Observable<PostEntity> postEntityObservable = Observable.just(postEntity);

        given(mockDiskPostDataStore.postEntityDetails(FAKE_POST_ID)).willReturn(postEntityObservable);


        mockDiskPostDataStore.postEntityDetails(FAKE_POST_ID);
        verify(mockLocalApi).postEntityById(FAKE_POST_ID);
    }
}