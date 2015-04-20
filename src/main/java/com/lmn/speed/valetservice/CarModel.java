package com.lmn.speed.valetservice;

/**
 * Created by lsouza on 16/04/2015.
 */
public enum CarModel {
    SMALL("Small"),
    FAMILY("Family"),
    MINI("Mini"),
    SALOON("Saloon"),
    EXECUTIVE("Executive"),
    MPV("MPV"),
    _4X4("4X4");

    private String modelName;

    CarModel(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }
}
