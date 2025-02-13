package dev.lieonlion.goggleplacement.config;

public class GpDefault implements GpUtil {
    @Override
    public boolean disabledMovingGogglesWithHelmet() {
        return true;
    }
    @Override
    public double gogglePossition() {
        return -0.25;
    }
}
