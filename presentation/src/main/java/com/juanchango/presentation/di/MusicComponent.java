package com.juanchango.presentation.di;

import com.juanchango.presentation.sample.MusicPlayer;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface MusicComponent {
    MusicPlayer getMusicPlayer();
}
