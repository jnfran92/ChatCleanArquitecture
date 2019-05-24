package com.juanchango.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.juanchango.presentation.di.DaggerMusicComponent;
import com.juanchango.presentation.di.MusicComponent;
import com.juanchango.presentation.sample.MusicPlayer;
import com.speakliz.chatcleanarquitecture.R;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Timber.plant(new Timber.DebugTree());

        MusicComponent musicComponent = DaggerMusicComponent.create();

        MusicPlayer musicPlayer = musicComponent.getMusicPlayer();

        musicPlayer.turnOn();
        musicPlayer.play();
        musicPlayer.pause();
        musicPlayer.stop();


        Timber.i("on Create using timber");

    }





}
