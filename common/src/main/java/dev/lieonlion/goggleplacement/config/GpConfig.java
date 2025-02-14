package dev.lieonlion.goggleplacement.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class GpConfig extends MidnightConfig {
    @Comment(centered = true) public static Comment woh;
    @Entry(isSlider = true, min = -0.5, max = 0.5) public static double wohGogglePlacement = 0.5;
    @Entry public static boolean wohFlipUpsideDown = false;
    @Entry public static boolean wohHideGoggles = false;

    @Comment(centered = true) public static Comment wh;
    @Entry public static boolean whDisableGogglesMovingDown = true;
    @Entry(isSlider = true, min = -0.5, max = 0.5) public static double whGogglePlacement = 0.0;
    @Entry public static boolean whFlipUpsideDown = false;
    @Entry public static boolean whHideGoggles = false;
}
