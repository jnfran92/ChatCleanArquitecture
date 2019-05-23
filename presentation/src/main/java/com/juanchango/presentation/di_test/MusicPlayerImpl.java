package com.juanchango.presentation.di_test;

import android.util.Log;

import com.juanchango.presentation.di_test.power_supply.Battery;
import com.juanchango.presentation.di_test.source.MusicSource;

public class MusicPlayerImpl implements MusicPlayer{

    private static final String TAG = MusicPlayerImpl.class.getName();

    private MusicSource musicSource;
    private Battery battery;

    private boolean isOn = false;

    public MusicPlayerImpl(MusicSource musicSource, Battery battery) throws NoBatteriesException {

        if(battery == null){
            throw new NoBatteriesException();
        }

        this.musicSource = musicSource;
        this.battery = battery;
    }

    @Override
    public void turnOn() {
        String energy = battery.getEnergy();
        Log.d(TAG, "turnOn: using " + energy);

    }

    @Override
    public void play() {
        String source = musicSource.getData();
        Log.d(TAG, "playing: using " + source);
    }

    @Override
    public void pause() {
        String source = musicSource.getData();
        Log.d(TAG, "pausing: using " + source);
    }

    @Override
    public void stop() {
        String source = musicSource.getData();
        Log.d(TAG, "stopping: using " + source);
    }
}
