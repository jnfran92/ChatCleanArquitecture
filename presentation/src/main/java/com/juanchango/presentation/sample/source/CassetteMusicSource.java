package com.juanchango.presentation.sample.source;

public class CassetteMusicSource implements MusicSource{


    public CassetteMusicSource() {
    }

    @Override
    public String getAudioData() {
        return "This is music Data in Cassette format.";
    }
}
