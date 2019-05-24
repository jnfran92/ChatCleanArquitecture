package com.juanchango.presentation.sample.power_supply;

import javax.inject.Inject;

public class NineVoltsBattery implements Battery{

    @Inject
    public NineVoltsBattery() {
    }

    public String getEnergy(){
        return "This is Energy from 9V batteries";
    }
}
