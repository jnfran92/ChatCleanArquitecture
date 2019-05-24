package com.juanchango.presentation.sample.source;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MusicSourceFactory {

    @Inject
    MusicSourceFactory() {
    }

    public MusicSource createCassetteMusicSource(){
        return new CassetteMusicSource();
    }

    public MusicSource createCdMusicSource(){
        return new CdMusicSource();
    }

    public MusicSource createMp3MusicSource(){
        return new Mp3MusicSource();
    }

}
