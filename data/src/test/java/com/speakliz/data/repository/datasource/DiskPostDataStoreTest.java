package com.speakliz.data.repository.datasource;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.data.suppliers.local.LocalApi;

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

    @Mock private DiskPostDataStore mockDiskPostDataStore;
    @Mock private LocalApi mockLocalApi;

    @Before
    public void setUp(){
        mockDiskPostDataStore = new DiskPostDataStore(mockLocalApi);
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