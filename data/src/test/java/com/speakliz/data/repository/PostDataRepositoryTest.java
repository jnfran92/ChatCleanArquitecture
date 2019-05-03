package com.speakliz.data.repository;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.data.entity.mapper.PostEntityDataMapper;
import com.speakliz.data.repository.datasource.PostDataStore;
import com.speakliz.data.repository.datasource.PostDataStoreFactory;

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
    private PostDataStoreFactory mockPostDataStoreFactory;

    @Mock
    private PostEntityDataMapper mockPostEntityDataMapper;

    @Mock
    private PostDataStore mockPostDataStore;

    @Before
    public void setUp() {
        postDataRepository = PostDataRepository.getInstance(
                mockPostDataStoreFactory,
                mockPostEntityDataMapper);

        given(mockPostDataStoreFactory.createDiskDataStore()).willReturn(mockPostDataStore);
        given(mockPostDataStoreFactory.createCloudDataStore()).willReturn(mockPostDataStore);
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