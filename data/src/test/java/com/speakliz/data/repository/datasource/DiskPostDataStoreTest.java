package com.speakliz.data.repository.datasource;

import com.speakliz.data.utils.local.LocalApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DiskPostDataStoreTest {

    private static final int FADE_POST_ID = 12;

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

//    @Test
//    public void postEntityDetails() {
//    }
}