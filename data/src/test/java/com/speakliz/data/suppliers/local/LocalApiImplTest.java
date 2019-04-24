package com.speakliz.data.suppliers.local;

import com.speakliz.data.entity.PostEntity;
import com.speakliz.data.suppliers.local.LocalApi;
import com.speakliz.data.suppliers.local.LocalApiImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class LocalApiImplTest {

    private LocalApi localApi;

    @Before
    public void setUp(){
        localApi = new LocalApiImpl();
    }

    @Test
    public void postEntityList() {
        Observable<List<PostEntity>> listObservable = localApi.postEntityList();

        Observer<List<PostEntity>> listObserver = new Observer<List<PostEntity>>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(List<PostEntity> value) {
                System.out.println("onNext postEntityList");

                for (PostEntity postEntity:value){
                    System.out.println(postEntity.getPostId());
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        listObservable.subscribe(listObserver);

        System.out.println("postEntity");
    }

    @Test
    public void postEntityById() {


    }
}