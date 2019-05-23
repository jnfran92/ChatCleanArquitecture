package com.juanchango.data.suppliers.cache;

import android.content.Context;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.suppliers.cache.serializer.Serializer;
import com.juanchango.data.suppliers.utils.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostCacheImplTest {

    private static final int FAKE_POST_ID = 666;

    @Mock
    private Context contextMock;

    @Mock
    private FileManager fileManagerMock;

    @Mock
    private File cacheDirMock;

    @Mock
    private ThreadExecutor threadExecutorMock;

    @Mock
    private Serializer serializerMock;

    @InjectMocks
    private PostCacheImpl postCacheImpl;

    @Before
    public void setUp(){
    }

    @Test
    public void testGetPostEntityHappyCase(){
        Observable<PostEntity> postEntityObservable = postCacheImpl.get(FAKE_POST_ID);
    }

    @Test
    public void testIsCached(){
        when(fileManagerMock.exists(any(File.class))).thenReturn(true);
        postCacheImpl.isCached(FAKE_POST_ID);
        verify(fileManagerMock).exists(any(File.class));
    }

}