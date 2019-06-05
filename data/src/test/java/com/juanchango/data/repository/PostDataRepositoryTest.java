package com.juanchango.data.repository;

import com.juanchango.data.entity.PostEntity;
import com.juanchango.data.entity.mapper.PostFromEntityMapper;
import com.juanchango.data.repository.datasource.PostDataSource;
import com.juanchango.data.repository.datasource.PostDataSourceFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostDataRepositoryTest {

    private PostDataRepository postDataRepository;

    @Mock
    private PostDataSourceFactory mockPostDataStoreFactory;

    @Mock
    private PostFromEntityMapper mockPostEntityDataMapper;

    @Mock
    private PostDataSource mockPostDataStore;

    @Before
    public void setUp() {
//        postDataRepository = PostDataRepository.getInstance(
//                mockPostDataStoreFactory,
//                mockPostEntityDataMapper);
//
//        given(mockPostDataStoreFactory.createDiskDataStore()).willReturn(mockPostDataStore);
//        given(mockPostDataStoreFactory.createCloudDataStore()).willReturn(mockPostDataStore);
    }


    @Test
    public void testGetPostsHappyCase(){
        List<PostEntity> postEntityList = new ArrayList<>();
        postEntityList.add(new PostEntity());

        given(mockPostDataStore.postEntityList()).willReturn(Observable.just(postEntityList));


        postDataRepository.posts();

        verify(mockPostDataStoreFactory).createCloudDataStore();
        verify(mockPostDataStore).postEntityList();

    }


}