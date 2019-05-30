package com.juanchango.presentation.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.speakliz.chatcleanarquitecture.R;

import timber.log.Timber;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.plant(new Timber.DebugTree());
        Timber.i("on Create using timber");
    }
}
