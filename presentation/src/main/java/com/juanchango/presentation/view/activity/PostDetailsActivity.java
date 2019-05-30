package com.juanchango.presentation.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.juanchango.presentation.R;

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
