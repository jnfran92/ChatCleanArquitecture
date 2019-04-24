package com.speakliz.data.repository;

import com.speakliz.data.entity.mapper.PostEntityDataMapper;
import com.speakliz.data.repository.datasource.PostDataStore;
import com.speakliz.data.repository.datasource.PostDataStoreFactory;
import com.speakliz.domain.model.Post;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;

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
//        postDataRepository = PostDataRepository.getInstance(mockPostDataStoreFactory,
//                mockPostEntityDataMapper);

//        given(mockPostDataStoreFactory.createDiskDataStore()).willReturn(mockPostDataStore);
    }


    @Test
    public void testGetPostData(){
        PostDataStoreFactory postDataStoreFactory = PostDataStoreFactory.getInstance();
        PostEntityDataMapper postEntityDataMapper = PostEntityDataMapper.getInstance();

        postDataRepository = PostDataRepository.getInstance(postDataStoreFactory,
                postEntityDataMapper);

        Observable<List<Post>> postsTest = postDataRepository.posts();

    }


}