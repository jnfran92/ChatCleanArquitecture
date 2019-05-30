package com.juanchango.presentation.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.juanchango.data.entity.mapper.PostEntityDataMapper;
import com.juanchango.data.executor.JobExecutor;
import com.juanchango.data.repository.PostDataRepository;
import com.juanchango.data.repository.datasource.PostDataStoreFactory;
import com.juanchango.data.suppliers.cache.FileManager;
import com.juanchango.data.suppliers.cache.PostCache;
import com.juanchango.data.suppliers.cache.PostCacheImpl;
import com.juanchango.data.suppliers.cache.serializer.Serializer;
import com.juanchango.domain.executor.PostExecutionThread;
import com.juanchango.domain.executor.ThreadExecutor;
import com.juanchango.domain.interactor.GetPostList;
import com.juanchango.domain.repository.PostRepository;
import com.juanchango.presentation.R;
import com.juanchango.presentation.UiThread;

import java.io.File;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("onResume");

    }
}
