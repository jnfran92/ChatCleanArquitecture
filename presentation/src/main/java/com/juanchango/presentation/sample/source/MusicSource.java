package com.juanchango.presentation.sample.source;

import javax.inject.Inject;

public class MusicSource {

    @Inject
    public MusicSource() {
    }

    public String getData(){
        return "This is music Data in MP3 format.";
    }

}
