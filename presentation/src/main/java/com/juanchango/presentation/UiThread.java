package com.juanchango.presentation;

import com.juanchango.domain.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Singleton
public class UiThread implements PostExecutionThread {

    @Inject
    UiThread() {
    }

    private static UiThread instance;
    public static PostExecutionThread getInstance() {
        if(instance == null){
            instance = new UiThread();
        }
        return instance;
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
