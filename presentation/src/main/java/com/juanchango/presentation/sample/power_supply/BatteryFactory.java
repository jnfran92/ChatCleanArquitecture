package com.juanchango.presentation.sample.power_supply;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BatteryFactory {

    @Inject
    BatteryFactory() {
    }

    public Battery create9VBattery(){
        return new NineVoltsBattery();
    }

    public Battery createAaBattery(){
        return new AaBattery();
    }

    public Battery createAaaBattery(){
        return new AaaBattery();
    }

}
