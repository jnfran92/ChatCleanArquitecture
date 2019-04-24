package com.speakliz.data.utils.local;

import com.speakliz.data.entity.PostEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;


public class LocalApiImplTest {

    LocalApi localApi;

    @Before
    public void setUp(){
//        LocalApi localApi = new LocalApiImpl();
    }

    @Test
    public void postEntityList() {
        localApi = new LocalApiImpl();
        Observable<List<PostEntity>> listObservable = localApi.postEntityList();
    }

    @Test
    public void postEntityById() {


    }
}