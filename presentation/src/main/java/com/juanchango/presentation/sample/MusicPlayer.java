package com.juanchango.presentation.sample;

import com.juanchango.presentation.sample.power_supply.Battery;
import com.juanchango.presentation.sample.power_supply.BatteryFactory;
import com.juanchango.presentation.sample.source.MusicSource;
import com.juanchango.presentation.sample.source.MusicSourceFactory;

import javax.inject.Inject;

import timber.log.Timber;

public class MusicPlayer {

    private static final String TAG = MusicPlayer.class.getName();

    private final MusicSourceFactory musicSourceFactory;
    private final BatteryFactory batteryFactory;

    private boolean isOn = false;

    @Inject
    MusicPlayer(MusicSourceFactory musicSourceFactory, BatteryFactory batteryFactory) {
        this.musicSourceFactory = musicSourceFactory;
        this.batteryFactory = batteryFactory;
    }

    private MusicSource musicSource;
    public void turnOn() {
        Battery battery = batteryFactory.create9VBattery();
        musicSource = musicSourceFactory.createCassetteMusicSource();

        String energy = battery.getEnergy();
        Timber.d("playing: using %s", energy);
    }


    public void play() {
        String source = musicSource.getAudioData();
        Timber.d("playing: using %s", source);
    }


    public void pause() {
        String source = musicSource.getAudioData();
        Timber.d("pausing: using %s", source);
    }


    public void stop() {
        String source = musicSource.getAudioData();
        Timber.d("stopping: using %s", source);
    }
}
