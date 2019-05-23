package com.juanchango.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juanchango.presentation.di.DaggerMusicComponent;
import com.juanchango.presentation.di.MusicComponent;
import com.juanchango.presentation.sample.MusicPlayer;
import com.juanchango.presentation.sample.MusicPlayer2;
import com.juanchango.presentation.sample.MusicPlayerImpl;
import com.juanchango.presentation.sample.exception.NoBatteriesException;
import com.juanchango.presentation.sample.power_supply.Battery;
import com.juanchango.presentation.sample.source.MusicSource;
import com.speakliz.chatcleanarquitecture.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MusicComponent musicComponent = DaggerMusicComponent.create();

        MusicPlayer2 musicPlayer = musicComponent.getMusicPlayer();

        musicPlayer.turnOn();
        musicPlayer.play();
        musicPlayer.pause();
        musicPlayer.stop();




    }





}
