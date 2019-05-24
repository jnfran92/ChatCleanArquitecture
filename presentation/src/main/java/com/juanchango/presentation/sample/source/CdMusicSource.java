package com.juanchango.presentation.sample.source;

public class CdMusicSource implements MusicSource{


    public CdMusicSource() {
    }

    @Override
    public String getAudioData() {
        return "This is music Data in CD format.";
    }
}
