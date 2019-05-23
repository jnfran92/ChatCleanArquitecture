package com.speakliz.data.suppliers.cache;

import android.content.Context;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.data.suppliers.cache.serializer.Serializer;
import com.speakliz.data.suppliers.utils.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostCacheImplTest {

    final int FAKE_POST_ID = 666;

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

    //    @InjectMocks
    private PostCacheImpl postCacheImpl;

    @Before
    public void setUp(){
        postCacheImpl = PostCacheImpl.getInstance(contextMock,
                fileManagerMock,
                cacheDirMock,
                threadExecutorMock,
                serializerMock);

        when(fileManagerMock.readFileContent(any(File.class))).thenReturn(null);
    }

    @Test
    public void testGetPostEntityHappyCase(){

        Observable<PostEntity> postEntityObservable = postCacheImpl.get(FAKE_POST_ID);

        verify(fileManagerMock).readFileContent(any(File.class));

    }


}