package com.speakliz.chatcleanarquitecture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.speakliz.chatcleanarquitecture.di_test.MusicPlayer;
import com.speakliz.chatcleanarquitecture.di_test.MusicPlayerImpl;
import com.speakliz.chatcleanarquitecture.di_test.NoBatteriesException;
import com.speakliz.chatcleanarquitecture.di_test.power_supply.Battery;
import com.speakliz.chatcleanarquitecture.di_test.source.MusicSource;

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
