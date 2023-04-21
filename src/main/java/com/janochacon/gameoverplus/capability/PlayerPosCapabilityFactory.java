package com.janochacon.gameoverplus.capability;

public class PlayerPosCapabilityFactory implements IPlayerPosCapability{

    private String dimension = "overworld";
    private double x = 0;
    private double y = 0;
    private double z = 0;

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public double getRX() {
        return x;
    }

    public void setRX(double x) {
        this.x = x;
    }

    public double getRY() {
        return y;
    }

    public void setRY(double y) {
        this.y = y;
    }

    public double getRZ() {
        return z;
    }

    public void setRZ(double z) {
        this.z = z;
    }
}
