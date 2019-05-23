package com.juanchango.presentation.sample;

import android.util.Log;

import com.juanchango.presentation.sample.power_supply.Battery;
import com.juanchango.presentation.sample.source.MusicSource;

import javax.inject.Inject;

public class MusicPlayer2 {


    private static final String TAG = MusicPlayerImpl.class.getName();

    private MusicSource musicSource;
    private Battery battery;

    private boolean isOn = false;

    @Inject
    public MusicPlayer2(MusicSource musicSource, Battery battery){
        this.musicSource = musicSource;
        this.battery = battery;
    }


    public void turnOn() {
        String energy = battery.getEnergy();
        Log.d(TAG, "turnOn: using " + energy);

    }


    public void play() {
        String source = musicSource.getData();
        Log.d(TAG, "playing: using " + source);
    }


    public void pause() {
        String source = musicSource.getData();
        Log.d(TAG, "pausing: using " + source);
    }


    public void stop() {
        String source = musicSource.getData();
        Log.d(TAG, "stopping: using " + source);
    }

}
