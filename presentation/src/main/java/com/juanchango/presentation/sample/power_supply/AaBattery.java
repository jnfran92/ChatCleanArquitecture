package com.juanchango.presentation.sample.power_supply;

import javax.inject.Inject;

public class AaBattery implements Battery{

    @Inject
    public AaBattery() {
    }

    public String getEnergy(){
        return "This is Energy from AA batteries";
    }
}
