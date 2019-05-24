package com.juanchango.presentation.sample.power_supply;

import javax.inject.Inject;

public class AaaBattery implements Battery{

    @Inject
    public AaaBattery() {
    }

    public String getEnergy(){
        return "This is Energy from AAA batteries";
    }
}
