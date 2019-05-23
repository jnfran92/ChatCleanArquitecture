package com.juanchango.presentation.di;

import com.juanchango.presentation.sample.MusicPlayer;
import com.juanchango.presentation.sample.MusicPlayer2;

import dagger.Component;

@Component
public
interface MusicComponent {
    MusicPlayer2 getMusicPlayer();
}
