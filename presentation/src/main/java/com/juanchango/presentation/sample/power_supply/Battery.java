package com.juanchango.presentation.sample.power_supply;

import javax.inject.Inject;

public class Battery {

    @Inject
    public Battery() {
    }

    public String getEnergy(){
        return "This is Energy from AA batteries";
    }
}
