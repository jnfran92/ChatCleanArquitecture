package com.juanchango.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juanchango.presentation.di_test.MusicPlayer;
import com.juanchango.presentation.di_test.MusicPlayerImpl;
import com.juanchango.presentation.di_test.NoBatteriesException;
import com.juanchango.presentation.di_test.power_supply.Battery;
import com.juanchango.presentation.di_test.source.MusicSource;
import com.speakliz.chatcleanarquitecture.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Battery battery = new Battery();
        MusicSource musicSource = new MusicSource();


        try {

            MusicPlayer musicPlayer = new MusicPlayerImpl(musicSource, battery);

            musicPlayer.play();
            musicPlayer.pause();
            musicPlayer.stop();


        } catch (NoBatteriesException e) {
            e.printStackTrace();
        }


    }





}
