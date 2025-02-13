package dev.lieonlion.goggleplacement.config;


public interface GpUtil {
    GpUtil DEFAULT = new GpDefault();

    boolean disabledMovingGogglesWithHelmet();
    double gogglePossition();
}
