package com.juanchango.presentation.sample.source;

public class Mp3MusicSource implements MusicSource{

    public Mp3MusicSource() {
    }

    @Override
    public String getAudioData() {
        return "This is music Data in MP3 format.";
    }
}
